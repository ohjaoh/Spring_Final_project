package Job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Job.entity.Board;
import Job.entity.BoardCategory;
import Job.service.BoardCategoryService;
import Job.service.BoardService;

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
		System.out.println("카테고리 요청 진입: " + categoryName);

		// 카테고리 조회
		BoardCategory category = boardCategoryService.findBoardCategory(categoryName);
		if (category == null) {
			System.err.println("카테고리를 찾을 수 없습니다: " + categoryName);
			return "error/404"; // 에러 페이지로 반환
		}

		// 게시글 조회
		List<Board> filteredBoards = boardService.boardList(category);
		System.out.println("게시글 개수: " + filteredBoards.size());

		// 모델에 데이터 추가
		model.addAttribute("boardList", filteredBoards);
		return "fragments/BoardList-Oh :: BoardList-Oh"; // 정확한 경로와 프래그먼트 이름

	}

}
