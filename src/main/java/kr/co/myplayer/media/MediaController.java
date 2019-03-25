package kr.co.myplayer.media;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.utility.UploadSaveManager;
import net.utility.Utility;


@Controller
public class MediaController {
	
	@Autowired
	private MediaDAO dao;
	
	public MediaController() {
		System.out.println("Start MediaController");
	}
	
	
	/*
	@RequestMapping(value="[요청명령어]", method=RequestMethod.[GET|POST], produces="text/plain; charset='UTF-8'")
	 */

	@RequestMapping(value="media/list.do", method=RequestMethod.GET)
	public ModelAndView list(MediaDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("media/list");
		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list", dao.list(dto));	// list 전달
		mav.addObject("mediagroupno", dto.getMediagroupno());
		
		return mav;
	} // list() end

	
	@RequestMapping(value="media/play.do", method=RequestMethod.GET)
	public ModelAndView read(MediaDTO dto) {
		ModelAndView mav=new ModelAndView();
			
		dto=dao.read(dto.getMediano());
		
		if(dto!=null) {
			String filename=dto.getFilename();
			filename=filename.toLowerCase();	// 소문자 변환
			if(filename.endsWith(".mp3")) mav.setViewName("media/readAudio");
			else if(filename.endsWith(".mp4")) mav.setViewName("media/readVideo");
		}
		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list", dao.list(dto));	// list 전달
		mav.addObject("article", dto);	// list 전달
		
		return mav;
	} // list() end
	

	@RequestMapping(value="media/create.do", method=RequestMethod.GET)
	public ModelAndView create(MediaDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("media/createForm");

		mav.addObject("root", Utility.getRoot());
		mav.addObject("mediagroupno", dto.getMediagroupno());
		
		return mav;
	} // create() end
	

	@RequestMapping(value="media/create.do", method=RequestMethod.POST)
	public ModelAndView createProc(MediaDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		System.out.println(dto.getPosterMF());
		
		mav.setViewName("media/msgView");
		
		// 전송된 파일 처리
		// 실제 파일: storage폴더, 파일관련 정보: media테이블
		
		String basePath=req.getRealPath("/media/storage");
		System.out.println("basePath: "+basePath);
		
		MultipartFile posterMF=dto.getPosterMF();
		System.out.println("posterMF: "+posterMF);
		String poster=UploadSaveManager.saveFileSpring30(posterMF, basePath);	// 파일 저장 후 rename된 파일명 반환
		System.out.println("poster: "+poster);
		dto.setPoster(poster);
		
		MultipartFile filenameMF=dto.getFilenameMF();
		String filename=UploadSaveManager.saveFileSpring30(filenameMF, basePath);	// 파일 저장 후 rename된 파일명 반환
		dto.setFilename(filename);
		dto.setFilesize(filenameMF.getSize());

		int result=dao.create(dto);
		
		if(result==0) {
			mav.addObject("msg1", "등록이 실패하였습니다. ");
			mav.addObject("img", "<img src='../images/original_17.gif'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do?mediagroupno="+dto.getMediagroupno()+"\"'>");
		}else {
			mav.addObject("title", dto.getTitle());
			mav.addObject("msg1", "등록되었습니다. ");
			mav.addObject("img", "<img src='../images/muzi2.gif'>");
			mav.addObject("link1", "<input type='button' value='계속 등록' onclick='location.href=\"./create.do?mediagroupno="+dto.getMediagroupno()+"\"'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do?mediagroupno="+dto.getMediagroupno()+"\"'>");
		}
		
		return mav;
	} // createProc() end
	

	@RequestMapping(value="media/update.do", method=RequestMethod.GET)
	public ModelAndView update(MediaDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("media/updateForm");

		dto=dao.read(dto.getMediano());
		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("article", dto);
		
		return mav;
	} // update() end
	

	@RequestMapping(value="media/update.do", method=RequestMethod.POST)
	public ModelAndView updateProc(MediaDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("media/msgView");
		
		// 전송된 파일 처리
		// 실제 파일: storage폴더, 파일관련 정보: media테이블
		
		System.out.println(dto.getMediano());
		
		String basePath=req.getRealPath("/media/storage");
		System.out.println("basePath: "+basePath);
		
		Boolean isModifyFile=false;

		if(dto.getPosterMF().getSize()>0) {	// 포스터 파일 수정 시 (포스터 업로드)
			isModifyFile=true;
			MultipartFile posterMF=dto.getPosterMF();
			System.out.println("posterMF: "+posterMF);
			String poster=UploadSaveManager.saveFileSpring30(posterMF, basePath);	// 파일 저장 후 rename된 파일명 반환
			System.out.println("poster: "+poster);
			dto.setPoster(poster);
		}

		if(dto.getFilenameMF().getSize()>0) {	// 미디어 파일 수정 시 (미디어파일 업로드)
			isModifyFile=true;
			MultipartFile filenameMF=dto.getFilenameMF();
			String filename=UploadSaveManager.saveFileSpring30(filenameMF, basePath);	// 파일 저장 후 rename된 파일명 반환
			dto.setFilename(filename);
			dto.setFilesize(filenameMF.getSize());
		}
		
		// DB 수정 및 기존 파일 삭제 수행
		int result;
		if(isModifyFile) result=dao.update(dto, basePath);
		else result=dao.update(dto);
		
		if(result==0) {
			mav.addObject("msg1", "수정 실패하였습니다. ");
			mav.addObject("img", "<img src='../images/original_17.gif'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do?mediagroupno="+dto.getMediagroupno()+"\"'>");
		}else {
//			mav.addObject("article", dto);
			mav.addObject("msg1", "수정되었습니다. ");
			mav.addObject("img", "<img src='../images/muzi2.gif'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do?mediagroupno="+dto.getMediagroupno()+"\"'>");
		}
		
		return mav;
	} // updateProc() end
	

	@RequestMapping(value="media/delete.do", method=RequestMethod.GET)
	public ModelAndView delete(MediaDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("media/delete");
		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("article", dto);
		
		return mav;
	} // delete() end
	

	@RequestMapping(value="media/delete.do", method=RequestMethod.POST)
	public ModelAndView deleteProc(MediaDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("media/msgView");

		String basePath=req.getRealPath("/media/storage");
		System.out.println("basePath: "+basePath);

		// DB 및 파일 삭제 수행
		int result=dao.delete(dto, basePath);
		
		if(result==0) {
			mav.addObject("msg1", "삭제 실패하였습니다. ");
			mav.addObject("img", "<img src='../images/original_17.gif'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do\"'>");
		}else {
//			mav.addObject("article", dto);
			mav.addObject("msg1", "삭제되었습니다. ");
			mav.addObject("img", "<img src='../images/muzi2.gif'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do?mediagroupno="+dto.getMediagroupno()+"\"'>");
		}
		
		return mav;
	} // deleteProc() end
	
	
}
