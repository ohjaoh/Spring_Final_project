package Job.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Job.entity.Board;
import Job.entity.BoardCategory;
import Job.entity.LoginInfo;
import Job.entity.User;
import Job.service.AdminService;
import Job.service.BoardCategoryService;
import Job.service.BoardService;
import Job.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private BoardCategoryService boardCategoryService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;

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

	@GetMapping("/admin/adminBoard/{id}")
	public String getAdminCategory(@PathVariable("id") String categoryName, Model model) {
		System.out.println("카테고리 요청 진입: " + categoryName);

		if (categoryName.equals("All")) {
			System.out.println("전체검색");
			// 게시글 조회
			List<Board> filteredBoards = boardService.findAll();

			// 모델에 데이터 추가
			model.addAttribute("boardList", filteredBoards);
		} else {
			// 카테고리 조회
			BoardCategory category = boardCategoryService.findBoardCategory(categoryName);
			if (category == null) {
				System.err.println("카테고리를 찾을 수 없습니다: " + categoryName);
				return "error/404"; // 에러 페이지로 반환
			}

			// 게시글 조회
			List<Board> filteredBoards = boardService.boardList(category);
//		System.out.println("게시글 개수: " + filteredBoards.size());

			// 모델에 데이터 추가
			model.addAttribute("boardList", filteredBoards);

		}
		return "fragments/admin/adminBoardList :: BoardList"; // 정확한 경로와 프래그먼트 이름

	}

	@GetMapping("/admin/adminCategory")
	public String getAdminCategory(Model model) {
		System.out.println("카테고리 요청 진입");

		// 카테고리목록 조회
		List<BoardCategory> boardCategoryList = boardCategoryService.BoardCategoryList();
//		System.out.println("게시글 개수: " + filteredBoards.size());

		// 모델에 데이터 추가
		model.addAttribute("UserList", boardCategoryList);

		return "fragments/admin/AdminCategory :: AdminCategory"; // 정확한 경로와 프래그먼트 이름

	}

	@GetMapping("/admin/adminUsers")
	public String getAdminUsers(Model model) {
		System.out.println("회원목록 요청 진입");

		// 회원명단 조회
		List<User> userList = userService.getUserList();
//		System.out.println("게시글 개수: " + filteredBoards.size());

		// 모델에 데이터 추가
		model.addAttribute("UserList", userList);

		return "fragments/admin/AdminUser :: AdminUser"; // 정확한 경로와 프래그먼트 이름

	}

}
