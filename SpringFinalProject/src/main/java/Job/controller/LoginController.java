package Job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Job.entity.Admin;
import Job.entity.User;
import Job.service.AdminService;
import Job.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	// 루트
	@GetMapping("/")
	public String rootPage() {
		return "index";
	}

	// 로그인
	@PostMapping("/login")
	public String adminLogin(@RequestParam("id") String id, @RequestParam("password") String password,
			HttpSession session) {
		System.out.println("입력한 id와 비밀번호 " + id + "비밀번호 " + password);
		if (adminService.AdminLogin(id, password)) {
			// 로그인 성공 시 관리자의 이름을 보여준다.
			session.setAttribute("AdminName", adminService.LoginAdminName(id));
			// 지금은 임시로 바로 관리자로 진입하게 했지만 추후에는 index.html에 버튼형태로 진입하게 수정할 계획
			return "redirect:admin";
		} else if (userService.userLogin(id, password)) {
			session.setAttribute("UserName", userService.LoginUserName(id));
			System.out.println("일반 회원 로그인 성공");
		}
		return "/";
	}

}
