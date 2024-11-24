package Job.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Job.entity.BoardCategory;
import Job.entity.LoginInfo;
import Job.service.AdminService;
import Job.service.BoardCategoryService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private BoardCategoryService boardCategoryService;

	// 관리자 페이지 진입
	@GetMapping("/admin")
	public String adminPage(HttpSession session, Model model) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
		if (loginInfo == null) {
			System.out.println("로그인한 사용자 정보가 없습니다.");
			// 세션값이 없으면 루트페이지로 리다이렉트
			return "redirect:/";
		}

		// 카테고리항목을 받아와서 그 중 이름만 추출하여 모델에 추가
		List<BoardCategory> boardCategoryList = boardCategoryService.BoardCategoryList();
		List<String> boardCategoryNameList = new ArrayList<>();

		// boardCategoryList에서 boardCategoryName만 추출하는 반복문
		for (BoardCategory boardCategory : boardCategoryList) {
			boardCategoryNameList.add(boardCategory.getCategoryName());
		}

		model.addAttribute("LoginInfo", loginInfo);
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
