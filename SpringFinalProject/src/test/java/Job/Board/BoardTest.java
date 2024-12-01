package Job.Board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import Job.entity.Board;
import Job.entity.BoardCategory;
import Job.entity.LoginInfo;
import Job.service.BoardServiceImpl;

@SpringBootTest
public class BoardTest {

	@Autowired
	private BoardServiceImpl boardServiceImpl;

	// 게시판 저장 test
//	@Test
	public void insertBoardTest() {
		LoginInfo userInfo = new LoginInfo();
		userInfo.setLoginId("관리자");
		String boardCategory = "구직";

		for (int i = 0; i < 5; i++) {
			Board board = new Board();
			board.setBoardTitle("구직게시글 제목" + i);
			board.setBoardContent("구직게시글 내용" + i);
			board.setWriterId("tester" + i);

			boardServiceImpl.insertBoard(board, boardCategory, userInfo);
		}

	}

	// 조회와 수정
//	@Test
	public void updateBoardTest() {
		Long boardNo = (long) 2;
		LoginInfo loginInfo = new LoginInfo();

		loginInfo.setLoginId("관리자");
		// 조회
		Board board = boardServiceImpl.boardPage(boardNo);
		// 수정
		board.setBoardContent("수정된 내용입니다. 안녕하세요.");
		boardServiceImpl.updateBoard(board, loginInfo);
	}

	// 삭제
//	@Test
	public void deleteBoardTest() {
		LoginInfo userInfo = new LoginInfo();
		userInfo.setLoginId("관리자");
		Long boardNo = (long) 6;
		boardServiceImpl.deleteBoard(boardNo, userInfo);
	}

	// 게시판 목록
	@Test
	public void boardListWithPagination() {
		// 카테고리 설정
		BoardCategory boardCategory = new BoardCategory();
		boardCategory.setBoardCategoryNo(1); // 테스트용 카테고리 번호

		// 페이지와 사이즈 설정
		int page = 0; // 첫 번째 페이지
		int size = 10; // 페이지당 항목 수
		Pageable pageable = PageRequest.of(page, size);

		// 게시판 목록 조회
		Page<Board> boardPage = boardServiceImpl.boardList(boardCategory, pageable);

		// 테스트 결과 출력
		System.out.println("Current Page: " + boardPage.getNumber());
		System.out.println("Total Pages: " + boardPage.getTotalPages());
		System.out.println("Total Items: " + boardPage.getTotalElements());
		System.out.println("Page Size: " + boardPage.getSize());
		System.out.println("Content: " + boardPage.getContent());
	}

}
