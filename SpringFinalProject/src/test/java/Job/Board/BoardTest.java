package Job.Board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Job.entity.Board;
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
		String boardCategory = "구인";

		for (int i = 0; i < 5; i++) {
			Board board = new Board();
			board.setBoardTitle("게시글 제목" + i);
			board.setBoardContent("회사명: ABC Tech Solutions\r\n" + "채용 직무: 프론트엔드 개발자 (React.js, Vue.js)\r\n"
					+ "근무 지역: 서울 강남구\r\n" + "구인" + i);
			board.setwriterId("tester" + i);

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
		List<Board> boardList = boardServiceImpl.boardList();
		System.out.println(boardList);

	}
}
