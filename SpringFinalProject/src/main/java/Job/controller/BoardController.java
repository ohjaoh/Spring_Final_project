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
import Job.service.BoardCategoryService;
import Job.service.BoardService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardCategoryService boardCategoryService;

	@GetMapping("/jobOfferBoardList")
	public String getjobOfferBoardList() {
		return "board/jobOfferBoardList"; //
	}

	@GetMapping("/jobSearchBoardList")
	public String getjobSearchBoardList() {
		return "board/jobSearchBoardList"; //
	}

	@GetMapping("/board/category/{id}")
	public String getBoardByCategory(@PathVariable("id") String categoryName, Model model) {
//		System.out.println("카테고리 요청 진입: " + categoryName);

		// 카테고리 조회
		BoardCategory category = boardCategoryService.findBoardCategory(categoryName);
		if (category == null) {
			System.err.println("카테고리를 찾을 수 없습니다: " + categoryName);
			return "error/404"; // 에러 페이지로 반환
		}

		// 게시글 조회
		List<Board> filteredBoards = boardService.boardList(category);
//		System.out.println("게시글 개수: " + filteredBoards.size());
		System.out.println(filteredBoards);

		for (int i = 1; i <= filteredBoards.size(); i++) {
			Board board = filteredBoards.get(i - 1);
			board.setBoardNo(Long.valueOf(i));

			filteredBoards.set(i - 1, board);
		}

		// 모델에 데이터 추가
		model.addAttribute("boardList", filteredBoards);
		return "fragments/BoardList :: BoardList"; // 정확한 경로와 프래그먼트 이름

	}

	@GetMapping("/boardWrite")
	public String boardWrite(HttpSession session, Model model) {

		LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

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
}
