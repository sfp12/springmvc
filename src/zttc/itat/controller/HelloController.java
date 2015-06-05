package zttc.itat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping({"/hello","/"})
	public ModelAndView hello(@RequestParam("username") String username){
		ModelAndView mv = new ModelAndView();
		mv.addObject("username", username);
		mv.setViewName("hello");
		return mv;
	}

}
