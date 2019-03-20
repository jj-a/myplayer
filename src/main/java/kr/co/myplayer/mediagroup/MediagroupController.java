package kr.co.myplayer.mediagroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Utility;


@Controller
public class MediagroupController {
	
	@Autowired
	private MediagroupDAO dao;
	
	public MediagroupController() {
		System.out.println("Start MediagroupController");
	}
	
	
	/*
	@RequestMapping(value="[요청명령어]", method=RequestMethod.[GET|POST], produces="text/plain; charset='UTF-8'")
	 */

	@RequestMapping(value="mediagroup/list.do", method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("mediagroup/list");
		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list", dao.list());	// list 전달
		
		return mav;
	} // list() end
	

	@RequestMapping(value="mediagroup/create.do", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/createForm");
		return mav;
	} // create() end
	

	@RequestMapping(value="mediagroup/create.do", method=RequestMethod.POST)
	public ModelAndView createProc(MediagroupDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("mediagroup/msgView");
		
		int result=dao.create(dto);
		
		if(result==0) {
			mav.addObject("msg1", "등록이 실패하였습니다. ");
			mav.addObject("img", "<img src='../images/original_17.gif'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do\"'>");
		}else {
			mav.addObject("title", dto.getTitle());
			mav.addObject("msg1", "등록되었습니다. ");
			mav.addObject("img", "<img src='../images/muzi2.gif'>");
			mav.addObject("link1", "<input type='button' value='계속 등록' onclick='location.href=\"./create.do\"'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do\"'>");
		}
		
		return mav;
	} // createProc() end
	

	@RequestMapping(value="mediagroup/update.do", method=RequestMethod.GET)
	public ModelAndView update(MediagroupDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("mediagroup/updateForm");

		dto=dao.show(dto.getMediagroupno());
		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("article", dto);
		
		return mav;
	} // update() end
	

	@RequestMapping(value="mediagroup/update.do", method=RequestMethod.POST)
	public ModelAndView updateProc(MediagroupDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("mediagroup/msgView");
		
		int result=dao.update(dto);
		
		if(result==0) {
			mav.addObject("msg1", "수정 실패하였습니다. ");
			mav.addObject("img", "<img src='../images/original_17.gif'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do\"'>");
		}else {
			mav.addObject("article", dto);
			mav.addObject("msg1", "수정되었습니다. ");
			mav.addObject("img", "<img src='../images/muzi2.gif'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do\"'>");
		}
		
		return mav;
	} // updateProc() end
	

	@RequestMapping(value="mediagroup/delete.do", method=RequestMethod.GET)
	public ModelAndView delete(MediagroupDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("mediagroup/delete");
		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("article", dto);
		
		return mav;
	} // delete() end
	

	@RequestMapping(value="mediagroup/delete.do", method=RequestMethod.POST)
	public ModelAndView deleteProc(MediagroupDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("mediagroup/msgView");

		int result=dao.delete(dto);
		
		if(result==0) {
			mav.addObject("msg1", "삭제 실패하였습니다. ");
			mav.addObject("img", "<img src='../images/original_17.gif'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do\"'>");
		}else {
			mav.addObject("article", dto);
			mav.addObject("msg1", "삭제되었습니다. ");
			mav.addObject("img", "<img src='../images/muzi2.gif'>");
			mav.addObject("link1", "<input type='button' value='목록' onclick='location.href=\"./list.do\"'>");
		}
		
		return mav;
	} // deleteProc() end
	
	
}
