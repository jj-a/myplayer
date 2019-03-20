package kr.co.myplayer.mediagroup;

import net.utility.DBOpen;
import net.utility.DBClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class MediagroupDAO {
	
	// @Autowired : @Component annotation으로 자동생성된 객체를 사용하기 위해 객체간 연결시켜줌
	@Autowired
	private DBOpen dbopen;
	@Autowired
	private DBClose dbclose;
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	StringBuffer sql=null;
	ArrayList<MediagroupDTO> list=null;
	
	
	// Constructor
	
	public MediagroupDAO() {
		System.out.println("Start MediagroupDAO");
		
	}
	
	
	
	// Method
	
	
	public int create(MediagroupDTO dto) {

		int res = 0;
		sql=new StringBuffer();
		
		try {
			con = dbopen.getConnection();
			
			sql.append("INSERT INTO mediagroup(mediagroupno, title) ");
			sql.append("VALUES((SELECT NVL(MAX(mediagroupno), 0)+1 FROM mediagroup), ?) ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());

			res = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("*Error* 행 추가를 실패했습니다. \n" + e);
		} finally {
			dbclose.close(con, pstmt);
		}

		return res;
		
	} // create() end
	
	
	
	public ArrayList<MediagroupDTO> list() {
		
		sql=new StringBuffer();

		try {
			con = dbopen.getConnection();
			
			sql.append("SELECT mediagroupno, title FROM mediagroup ");
			sql.append("ORDER BY mediagroupno ASC ");

			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				list = new ArrayList<MediagroupDTO>();
				do {
					// list에 데이터 하나씩 저장
					MediagroupDTO dto = new MediagroupDTO();
					dto.setMediagroupno(rs.getInt("mediagroupno"));
					dto.setTitle(rs.getString("title"));
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
	
	
	
	public MediagroupDTO show(int mediagroupno) {
		
		MediagroupDTO dto=null;
		sql=new StringBuffer();

		try {
			con = dbopen.getConnection();
			
			sql.append("SELECT mediagroupno, title FROM mediagroup ");
			sql.append("WHERE mediagroupno=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, mediagroupno);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
					dto = new MediagroupDTO();
					dto.setMediagroupno(rs.getInt("mediagroupno"));
					dto.setTitle(rs.getString("title"));
					
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
		
	} // show() end
	
	
	
	public int update(MediagroupDTO dto) {

		int res = 0;
		sql=new StringBuffer();
		
		try {
			con = dbopen.getConnection();

			sql.append("UPDATE mediagroup ");
			sql.append("SET title=? ");
			sql.append("WHERE mediagroupno=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());
			pstmt.setInt(2, dto.getMediagroupno());

			res = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("*Error* 행 수정을 실패했습니다. \n" + e);
		} finally {
			dbclose.close(con, pstmt);
		}
		
		return res;
		
	} // update() end
	
	

	public int delete(MediagroupDTO dto) {

		int res = 0;
		sql=new StringBuffer();
		
		try {
			con = dbopen.getConnection();

			sql.append("DELETE FROM mediagroup ");
			sql.append("WHERE mediagroupno=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getMediagroupno());

			res = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("*Error* 행 삭제를 실패했습니다. \n" + e);
		} finally {
			dbclose.close(con, pstmt);
		}
		
		return res;
		
	} // delete() end
	
	
	
}
