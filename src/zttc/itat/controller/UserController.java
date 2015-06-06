package zttc.itat.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import zttc.itat.model.User;
import zttc.itat.model.UserException;

@Controller
@RequestMapping("/user")
public class UserController {
	private Map<String, User> users = new HashMap<String, User>();
	
	public UserController(){
		users.put("a", new User("a","aa","1","s"));
		users.put("b", new User("b","bb","2","f"));
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("users", users);
		mv.setViewName("user/list");
		return mv;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(@ModelAttribute("user") User user){
		return "user/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@Valid User user, BindingResult br, @RequestParam MultipartFile attach, HttpServletRequest req) throws IOException{
		if(br.hasErrors()){			
			return "user/add";
		}
		String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
		System.out.println(realpath);
		File f = new File(realpath+"/"+attach.getOriginalFilename());
		FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
		users.put(user.getUsername(), user);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable String username){
		ModelAndView mv = new ModelAndView();
		mv.addObject(users.get(username));
		mv.setViewName("user/show");
		return mv;
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET, params="json")
	@ResponseBody
	public User show(@PathVariable String username, String abc){
		return users.get(username);
	}
	
	@RequestMapping(value="/{username}/update", method=RequestMethod.GET)
	public ModelAndView update(@PathVariable String username){
		ModelAndView mv = new ModelAndView();
		mv.addObject(users.get(username));
		mv.setViewName("user/update");
		return mv;
	}
	
	@RequestMapping(value="/{username}/update", method=RequestMethod.POST)
	public String update(@PathVariable String username, @Valid User user, BindingResult br){
		if(br.hasErrors()){			
			return "user/update";
		}
		users.put(username, user);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value="/{username}/delete", method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable String username){
		ModelAndView mv = new ModelAndView();
		users.remove(username);
		mv.setViewName("redirect:/user/users");
		return mv;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String username, String password, HttpSession session){
		if(!users.containsKey(username)){
			throw new UserException("用户名不存在");
		}
		User u = users.get(username);
		if(!u.getPassword().equals(password)){
			throw new UserException("用户密码不正确");
		}
		session.setAttribute("loginUser", u);		
		return "redirect:/user/users";
	}
	
//	@ExceptionHandler(value={UserException.class})
//	public String handlerException(UserException e, HttpServletRequest req){
//		req.setAttribute("e", e);
//		return "error";
//	}
	
	
	
	
	
	
	
	

}
