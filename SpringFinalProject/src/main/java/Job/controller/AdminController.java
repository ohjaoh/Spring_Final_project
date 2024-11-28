package Job.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import Job.entity.Board;
import Job.entity.BoardCategory;
import Job.entity.LoginInfo;
import Job.entity.User;
import Job.entity.UserLiteInfo;
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

	// 게시판관리
	@GetMapping("/admin/adminBoard/{id}")
	public String getAdminCategory(@PathVariable("id") String categoryName, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, Model model) {

		System.out.println("카테고리 요청 진입: " + categoryName);

		Pageable pageable = PageRequest.of(page, size); // 페이지네이션 설정

		if (categoryName.equals("All")) {
			System.out.println("전체검색");

			// 전체 게시글 조회 (페이지네이션)
			Page<Board> filteredBoards = boardService.findAll(pageable);

			// 모델에 데이터 추가
			model.addAttribute("boardList", filteredBoards.getContent());
			model.addAttribute("currentPage", filteredBoards.getNumber());
			model.addAttribute("totalPages", filteredBoards.getTotalPages());
			model.addAttribute("totalItems", filteredBoards.getTotalElements());

		} else {
			// 카테고리 조회
			BoardCategory category = boardCategoryService.findBoardCategory(categoryName);
			if (category == null) {
				System.err.println("카테고리를 찾을 수 없습니다: " + categoryName);
				return "error/404"; // 에러 페이지로 반환
			}

			// 카테고리별 게시글 조회 (페이지네이션)
			Page<Board> filteredBoards = boardService.boardList(category, pageable);

			// 모델에 데이터 추가
			model.addAttribute("boardList", filteredBoards.getContent());
			model.addAttribute("currentPage", filteredBoards.getNumber());
			model.addAttribute("totalPages", filteredBoards.getTotalPages());
			model.addAttribute("totalItems", filteredBoards.getTotalElements());
		}

		return "fragments/admin/adminBoardList :: BoardList"; // 정확한 경로와 프래그먼트 이름
	}

	// 카테고리관리
	@GetMapping("/admin/adminCategory")
	public String getAdminCategory(Model model) {
//		System.out.println("카테고리 요청 진입");

		// 카테고리목록 조회
		List<BoardCategory> boardCategoryList = boardCategoryService.BoardCategoryList();
//		System.out.println("카테고리 개수: " + boardCategoryList);

		// 모델에 데이터 추가
		model.addAttribute("boardCategoryList", boardCategoryList);

		return "fragments/admin/AdminCategory :: AdminCategory"; // 정확한 경로와 프래그먼트 이름

	}

	// 회원목록
	@GetMapping("/admin/adminUsers")
	public String getAdminUsers(Model model) {
//		System.out.println("회원목록 요청 진입");

		// 회원명단 조회
		List<UserLiteInfo> userList = userService.userList();
		System.out.println("회원수: " + userList);

		// 모델에 데이터 추가
		model.addAttribute("UserList", userList);

		return "fragments/admin/AdminUser :: AdminUser"; // 정확한 경로와 프래그먼트 이름

	}

	// 게시판삭제
	@GetMapping("/admin/deleteBoard/{boardNo}")
	public String adminDeleteBoard(@PathVariable("boardNo") Long boardNo, HttpSession session,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

		System.out.println("진입체크" + boardNo);
		boardService.deleteBoard(boardNo, loginInfo);

		Pageable pageable = PageRequest.of(page, size); // 페이지네이션 설정
		// 전체 게시글 조회 (페이지네이션)
		Page<Board> filteredBoards = boardService.findAll(pageable);

		// 모델에 데이터 추가
		model.addAttribute("boardList", filteredBoards.getContent());
		model.addAttribute("currentPage", filteredBoards.getNumber());
		model.addAttribute("totalPages", filteredBoards.getTotalPages());
		model.addAttribute("totalItems", filteredBoards.getTotalElements());
		// 모델에 데이터 추가
		model.addAttribute("boardList", filteredBoards);
		return "fragments/admin/adminBoardList :: BoardList"; // 정확한 경로와 프래그먼트 이름

	}
}
