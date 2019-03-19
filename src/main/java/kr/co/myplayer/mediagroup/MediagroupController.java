package kr.co.myplayer.mediagroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller ////////////////////////////////
public class MediagroupController {
	
	public MediagroupController() {
		System.out.println("Start MediagroupController");
	}
	
	/*
	@RequestMapping(value="[요청명령어]", method=RequestMethod.[GET|POST], produces="text/plain; charset='UTF-8'")
	 */
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public ModelAndView loginForm() {
	
		ModelAndView mav=new ModelAndView();
		// view페이지 이동 (prefix="/", suffix=".jsp")
		mav.setViewName("login/loginForm"); // /login/loginForm.jsp
		
		return mav;
		
	} // loginForm() end
	
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView loginProc(MediagroupDTO dto, HttpServletRequest req, HttpSession session) {
		
		int mno = dto.getMediagroupno();
		String title = dto.getTitle();
		
		
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("media/mediagroup");
		mav.addObject("mno", mno);
		mav.addObject("title", title);
		mav.addObject("msg", " ");
		
		return mav;
	} // loginProc() end
	

	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView loginProc() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("login/logout");
		return mav;
	} // logout() end
	
	
}
