package Job.controller;

import Job.entity.LoginInfo;
import Job.entity.User;
import Job.service.UserService;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("username", null); // 세션에서 사용자 이름 삭제
		return "redirect:/index"; // 로그아웃 후 메인 페이지로 리다이렉트
	}

	// 회원가입 페이지 진입
	@GetMapping("/registerPage")
	public String registerPage(Model model) {
		// 필요한 데이터 추가
		model.addAttribute("message", "회원가입 페이지로 이동합니다.");
		return "fragments/registerPage :: registerPage"; // registerPage의 content 프래그먼트 반환
	}

	// 마이페이지 진입
	@GetMapping("/user/myPage")
	public String myPage(HttpSession session, Model model) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

		User user = userService.getUser(loginInfo.getLoginId());

		System.out.println(user);
		model.addAttribute("user", user);
		return "fragments/myPage :: MyPage"; // 정확한 경로와 프래그먼트 이름
	}

	@PostMapping("/register")
	public String registerUser(User user) {
		userService.registerUser(user);

		return "redirect:/";
	}

	@GetMapping("/idCheck/{userId}")
	public ResponseEntity<Map<String, Boolean>> idCheck(@PathVariable("userId") String userId) {
		System.out.println("진입");
		boolean isAvailable = userService.idCheck(userId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("isAvailable", isAvailable);
		return ResponseEntity.ok(response);
	}
}
