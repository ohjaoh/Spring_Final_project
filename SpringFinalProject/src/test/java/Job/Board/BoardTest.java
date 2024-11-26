package Job.Board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		userInfo.setLoginName("관리자");
		Long boardNo = (long) 6;
		boardServiceImpl.deleteBoard(boardNo, userInfo);
	}

	// 게시판 목록
//	@Test
	public void boardList() {
		BoardCategory boardCategory = new BoardCategory();
		boardCategory.setBoardCategoryNo(1);
		List<Board> boardList = boardServiceImpl.boardList(boardCategory);
		System.out.println(boardList);

	}
}
