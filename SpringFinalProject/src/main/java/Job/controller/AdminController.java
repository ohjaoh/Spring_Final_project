package Job.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Job.entity.Admin;
import Job.entity.BoardCategory;
import Job.service.AdminService;
import Job.service.BoardCategoryService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private BoardCategoryService boardCategoryService;

	// 일단 임시로 index의 login을 이용해서 연결함 다음 주에 관리자 로그인을 분리하거나 login컨트롤러를 만들고 합칠 에정
	@PostMapping("/adminLogin")
	public String adminLogin(@RequestParam("userId") String id, @RequestParam("userId") String password,
			HttpSession session) {
		Admin admin = new Admin();
		admin.setAdminId(id);
		admin.setAdminPassword(password);
//		System.out.println("입력한 id와 비밀번호 " + admin.getAdminId() + "비밀번호 " + admin.getAdminPassword());
		if (adminService.AdminLogin(admin.getAdminId(), admin.getAdminPassword())) {
			// 로그인 성공 시 관리자의 이름을 보여준다.
			session.setAttribute("AdminName", adminService.LoginAdminName(id));
			// 지금은 임시로 바로 관리자로 진입하게 했지만 추후에는 index.html에 버튼형태로 진입하게 수정할 계획
			return "redirect:admin";
		}
		return "/";
	}

	// 관리자 페이지 진입
	@GetMapping("/admin")
	public String adminPage(HttpSession session, Model model) {
		String adminName = (String) session.getAttribute("AdminName");
		if (adminName == null) {
			// 세션에 AdminName이 없으면 루트페이지로 리다이렉트
			return "redirect:/";
		}
		// System.out.println(adminName);

		// 예제 데이터 실제로는 tag목록을 뽑아서 넣는 형태로 해야함
		List<BoardCategory> boardCategoryList = boardCategoryService.BoardCategoryList();
		List<String> boardCategoryNameList = new ArrayList<>();

		// boardCategoryList에서 boardCategoryName만 추출하는 반복문
		for (BoardCategory boardCategory : boardCategoryList) {
			boardCategoryNameList.add(boardCategory.getCategoryName());
		}

		model.addAttribute("AdminName", adminName);
		model.addAttribute("boardCategoryList", boardCategoryNameList);
		return "admin/Admin";
	}

	@GetMapping("/AdminBoardCategoryManagement")
	public String getCategories(Model model) {
		List<BoardCategory> boardCategoryList = boardCategoryService.BoardCategoryList();

		model.addAttribute("boardCategoryList", boardCategoryList);
		return "Admin::AdminBoardCategoryManagement";
	}
}
