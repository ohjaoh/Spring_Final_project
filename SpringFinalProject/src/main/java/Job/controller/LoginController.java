package Job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Job.entity.LoginInfo;
import Job.service.LoginService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	// 루트
	@GetMapping("/")
	public String rootPage(HttpSession session, Model model) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
		if (loginInfo != null) {
			System.out.println("로그인한 사용자 정보가 존재합니다.");
			model.addAttribute("LoginInfo", loginInfo);
			// 이거 기반으로 th:if 사용 가능하도록 백엔드만 수정
		}

		return "index";
	}

	// 로그인
	@PostMapping("/login")
	public String adminLogin(@RequestParam String id, @RequestParam String password, HttpSession session) {
//		System.out.println("입력한 id와 비밀번호 " + id + "비밀번호 " + password);
		if (loginService.AdminLogin(id, password)) {
			session.setAttribute("LoginInfo", loginService.LoginAdminName(id));
			// 지금은 임시로 바로 관리자로 진입하게 했지만 추후에는 index.html에 버튼형태로 진입하게 수정
			System.out.println("관리자 로그인 성공");
			return "redirect:admin";
		} else if (loginService.userLogin(id, password)) {
			session.setAttribute("LoginInfo", loginService.LoginUserName(id));
			System.out.println("일반 회원 로그인 성공");
		}
		return "redirect:/";
	}

}
