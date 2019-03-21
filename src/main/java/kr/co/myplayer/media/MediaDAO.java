package kr.co.myplayer.media;

import net.utility.DBOpen;
import net.utility.DBClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class MediaDAO {
	
	// @Autowired : @Component annotation으로 자동생성된 객체를 사용하기 위해 객체간 연결시켜줌
	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbclose;
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	StringBuffer sql=null;
	ArrayList<MediaDTO> list=null;
	
	
	// Constructor
	
	public MediaDAO() {
		System.out.println("Start MediaDAO");
		
	}
	
	
	
	// Method
	
	
	public int create(MediaDTO dto) {

		int res = 0;
		sql=new StringBuffer();
		
		try {
			con = dbopen.getConnection();
			
			sql.append("INSERT INTO media(mediano, title, poster, filename, filesize, rdate, mediagroupno) ");
			sql.append("VALUES((SELECT NVL(MAX(mediano), 0)+1 FROM media), ?, ?, ?, ?, sysdate, ?) ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getPoster());
			pstmt.setString(3, dto.getFilename());
			pstmt.setLong(4, dto.getFilesize());
			pstmt.setInt(5, dto.getMediagroupno());

			res = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("*Error* 행 추가를 실패했습니다. \n" + e);
		} finally {
			dbclose.close(con, pstmt);
		}

		return res;
		
	} // create() end
	
	
	
	public ArrayList<MediaDTO> list(MediaDTO dto) {
		
		sql=new StringBuffer();

		try {
			con = dbopen.getConnection();
			
			sql.append("SELECT mediano, title, poster, filename, filesize, mview, rdate, mediagroupno FROM media ");
			sql.append("WHERE mview='Y' AND mediagroupno=? ");
			sql.append("ORDER BY mediano ASC ");

			System.out.println(dto.getMediagroupno());
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getMediagroupno());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				list = new ArrayList<MediaDTO>();
				do {
					// list에 데이터 하나씩 저장
					dto = new MediaDTO();
					dto.setMediano(rs.getInt("mediano"));
					dto.setTitle(rs.getString("title"));
					dto.setPoster(rs.getString("poster"));
					dto.setFilename(rs.getString("filename"));
					dto.setFilesize(rs.getInt("filesize"));
					dto.setMview(rs.getString("Mview"));
					dto.setRdate(rs.getString("Rdate"));
					dto.setMediagroupno(rs.getInt("mediagroupno"));
					list.add(dto);

				} while (rs.next());
				
			} else {
				throw new Exception("rs.next()가 제대로 동작하지 않습니다. " 
			+ "Check: Query가 제대로 들어갔는지, next()가 중복 사용된건 아닌지 확인해주세요.");
			}

		} catch (Exception e) {
			System.out.println("*Error* 자료 조회를 실패했습니다. \n" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}

		return list;
		
	} // list() end
	
	
	
	public MediaDTO read(int mediano) {
		
		MediaDTO dto=null;
		sql=new StringBuffer();

		try {
			con = dbopen.getConnection();
			
			sql.append("SELECT mediano, title, poster, filename, filesize, mview, rdate, mediagroupno FROM media ");
			sql.append("WHERE mview='Y' AND mediano=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, mediano);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new MediaDTO();
				dto.setMediano(rs.getInt("mediano"));
				dto.setTitle(rs.getString("title"));
				dto.setPoster(rs.getString("poster"));
				dto.setFilename(rs.getString("filename"));
				dto.setFilesize(rs.getInt("filesize"));
				dto.setMview(rs.getString("Mview"));
				dto.setRdate(rs.getString("Rdate"));
				dto.setMediagroupno(rs.getInt("mediagroupno"));
					
			} else {
				throw new Exception("rs.next()가 제대로 동작하지 않습니다. " 
			+ "Check: Query가 제대로 들어갔는지, next()가 중복 사용된건 아닌지 확인해주세요.");
			}

		} catch (Exception e) {
			System.out.println("*Error* 자료 조회를 실패했습니다. \n" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}

		return dto;
		
	} // read() end
	
	
	
	public int update(MediaDTO dto) {

		int res = 0;
		sql=new StringBuffer();
		
		try {
			con = dbopen.getConnection();

			sql.append("UPDATE media ");
			sql.append("SET title=? ");
			sql.append("WHERE mediano=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());
			pstmt.setInt(2, dto.getMediano());

			res = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("*Error* 행 수정을 실패했습니다. \n" + e);
		} finally {
			dbclose.close(con, pstmt);
		}
		
		return res;
		
	} // update() end
	
	

	public int delete(MediaDTO dto) {

		int res = 0;
		sql=new StringBuffer();
		
		try {
			con = dbopen.getConnection();

			sql.append("DELETE FROM media ");
			sql.append("WHERE mediano=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getMediano());

			res = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("*Error* 행 삭제를 실패했습니다. \n" + e);
		} finally {
			dbclose.close(con, pstmt);
		}
		
		return res;
		
	} // delete() end
	
	
	
}
