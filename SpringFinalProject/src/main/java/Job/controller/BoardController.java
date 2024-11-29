package Job.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import Job.entity.Board;
import Job.entity.BoardCategory;
import Job.entity.LoginInfo;
import Job.service.BoardCategoryService;
import Job.service.BoardService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardCategoryService boardCategoryService;

	@GetMapping("/board/category/{id}")
	public String getBoardByCategory(@PathVariable("id") String categoryName,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
//		System.out.println("카테고리 요청 진입: " + categoryName);

		// 카테고리 조회
		BoardCategory category = boardCategoryService.findBoardCategory(categoryName);
		if (category == null) {
			System.err.println("카테고리를 찾을 수 없습니다: " + categoryName);
			return "error/404"; // 에러 페이지로 반환
		}

		Pageable pageable = PageRequest.of(page, size); // 페이지네이션 설정

		// 카테고리별 게시글 조회 (페이지네이션)
		Page<Board> filteredBoards = boardService.boardList(category, pageable);

		// 모델에 데이터 추가
		model.addAttribute("boardList", filteredBoards.getContent());
		model.addAttribute("currentPage", filteredBoards.getNumber());
		model.addAttribute("totalPages", filteredBoards.getTotalPages());
		model.addAttribute("totalItems", filteredBoards.getTotalElements());
		model.addAttribute("boardList", filteredBoards);
		return "fragments/BoardList :: BoardList";

	}

	// 글작성페이지 진입 메서드
	@GetMapping("/boardWrite")
	public String boardWrite(HttpSession session, Model model) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
		// 로그인 시에만 가능
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

		model.addAttribute("boardCategoryList", boardCategoryNameList);
		return "board/boardWrite";
	}

	// 게시판 작성페이지
	@PostMapping("/board/write")
	public String writeBoard(HttpSession session, @RequestParam String category, @RequestParam String title,
			@RequestParam String content) {

		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

		BoardCategory boardCategory = boardCategoryService.findBoardCategory(category);

		Board board = new Board();
		board.setBoardTitle(title);
		board.setCategory(boardCategory);
		board.setBoardContent(content);

		boardService.insertBoard(board, category, loginInfo);

		// 데이터 처리 로직
		return "redirect:/";
	}

	// 게시글 조회페이지
	@GetMapping("/boardPage/{boardNo}")
	public String boardPage(HttpSession session, @PathVariable("boardNo") Long boardNo, Model model) {

		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
//		System.out.println("진입체크" + boardNo);
		Board board = boardService.boardPage(boardNo);

//		System.out.println("에러체크" + board);
		model.addAttribute("board", board);
		model.addAttribute("LoginInfo", loginInfo);
		return "fragments/boardPage :: boardPage";
	}

	// 게시글 수정페이지 진입
	@GetMapping("/boardUpdate/{boardNo}")
	public String boardUpdatePage(HttpSession session, @PathVariable("boardNo") Long boardNo, Model model) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
		// 로그인 시에만 가능
		if (loginInfo == null) {
			System.out.println("로그인한 사용자 정보가 없습니다.");
			// 세션값이 없으면 루트페이지로 리다이렉트
			return "/";
		}

//		System.out.println("진입체크" + boardNo);
		Board board = boardService.boardPage(boardNo);

		// 카테고리항목을 받아와서 그 중 이름만 추출하여 모델에 추가
		List<BoardCategory> boardCategoryList = boardCategoryService.BoardCategoryList();
		List<String> boardCategoryNameList = new ArrayList<>();

		// boardCategoryList에서 boardCategoryName만 추출하는 반복문
		for (BoardCategory boardCategory : boardCategoryList) {
			boardCategoryNameList.add(boardCategory.getCategoryName());
		}

		model.addAttribute("boardCategoryList", boardCategoryNameList);
		model.addAttribute("board", board);
		model.addAttribute("LoginInfo", loginInfo);

		return "fragments/boardUpdatePage :: boardUpdate";
	}

	// 게시글 수정 메서드
	@PostMapping("/board/update/{boardNo}")
	public String boardUpdate(HttpSession session, @PathVariable Long boardNo,
			@RequestBody Map<String, String> requestData, Model model) {

		// 세션에 저장된 로그인정보를 불러온다.
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

		String boardTitle = requestData.get("boardTitle");
		String category = requestData.get("category");
		String boardContent = requestData.get("boardContent");

		// 카테고리객체에 맞게 이름을 기준으로 찾아서 가져온다.
		BoardCategory boardCategory = boardCategoryService.findBoardCategory(category);

		Board board =boardService.boardPage(boardNo);

		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setCategory(boardCategory);

		boardService.updateBoard(board, loginInfo);

		// 업데이트된 게시판 객체를 불러옴
		Board board2 = boardService.boardPage(boardNo);

		model.addAttribute("board", board2);
		model.addAttribute("LoginInfo", loginInfo);
		return "fragments/boardPage :: boardPage";
	}

	@GetMapping("/board/delete/{boardNo}")
	public String boardDelete(HttpSession session, @PathVariable("boardNo") Long boardNo,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {

		// 세션에 저장된 로그인정보를 불러온다.
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

		boardService.deleteBoard(boardNo, loginInfo);

		Board board = boardService.boardPage(boardNo);
		BoardCategory boardCategory = board.getCategory();
		String categoryName = boardCategory.getCategoryName();

		System.out.println("카테고리 요청 진입: " + categoryName);

		// 카테고리 조회
		BoardCategory category = boardCategoryService.findBoardCategory(categoryName);
		if (category == null) {
			System.err.println("카테고리를 찾을 수 없습니다: " + categoryName);
			return "error/404"; // 에러 페이지로 반환
		}
		
		Pageable pageable = PageRequest.of(page, size); // 페이지네이션 설정

		// 카테고리별 게시글 조회 (페이지네이션)
		Page<Board> filteredBoards = boardService.boardList(category, pageable);

		// 모델에 데이터 추가
		model.addAttribute("boardList", filteredBoards.getContent());
		model.addAttribute("currentPage", filteredBoards.getNumber());
		model.addAttribute("totalPages", filteredBoards.getTotalPages());
		model.addAttribute("totalItems", filteredBoards.getTotalElements());
		model.addAttribute("boardList", filteredBoards);
		return "fragments/BoardList :: BoardList";

	}

}
