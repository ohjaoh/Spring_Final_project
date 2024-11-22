package Job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Job.entity.Admin;
import Job.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	// 일단 임시로 index의 login을 이용해서 연결함 다음 주에 관리자 로그인을 분리하거나 login컨트롤러를 만들고 합칠 에정
	@PostMapping("/adminLogin")
	public String adminLogin(@RequestParam("userId") String id, @RequestParam("userId") String password) {
		Admin admin= new Admin();
		admin.setAdminId(id);
		admin.setAdminPassword(password);
		System.out.println("입력한 id와 비밀번호 " + admin.getAdminId() + "비밀번호 " + admin.getAdminPassword());
		if (adminService.AdminLogin(admin.getAdminId(), admin.getAdminPassword())) {
			return "redirect:admin";
		}
		return "/";
	}

	// 관리자 페이지 진입
	@GetMapping("/admin")
	public String adminPage(Model model) {
		// 예제 데이터 실제로는 tag목록을 뽑아서 넣는 형태로 해야함
		List<String> boardList = List.of("게시판1", "게시판2", "게시판3", "게시판4", "게시판5");
		model.addAttribute("boardList", boardList);
		return "admin/Admin";
	}

}
