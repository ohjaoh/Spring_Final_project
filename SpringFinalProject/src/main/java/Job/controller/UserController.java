package Job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Job.entity.User;
import Job.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/loginView")
	public String login() {
		
		return "login";
	}
	
	@PostMapping("login")
	public String login(User user) {
	
	User findUser = userService.getUser(user);
	if(findUser != null && findUser.getPassword().equals(user.getPassword()))
		return "forward:index";
	else
		return "ridrect:login";
	}

}
