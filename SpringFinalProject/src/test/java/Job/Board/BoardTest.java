package Job.Board;

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
	@Test
	public void insertBoardTest() {
		Board board = new Board();
		LoginInfo userInfo = new LoginInfo();
		userInfo.setLoginName("관리자");

		board.setBoardTitle("게시글 제목");
		board.setBoardContent(
				"회사명: ABC Tech Solutions\r\n" + "채용 직무: 프론트엔드 개발자 (React.js, Vue.js)\r\n" + "근무 지역: 서울 강남구\r\n" + "구인");
		board.setWriterName("testing");

		String boardCategory = "구인";
		boardServiceImpl.insertBoard(board, boardCategory, userInfo);

	}

	// 조회와 수정
//	@Test
	public void updateBoardTest() {
		Long boardNo = (long) 2;

		Board board = boardServiceImpl.boardPage(boardNo);
		board.setBoardContent("수정된 내용입니다. 안녕하세요.");
		boardServiceImpl.updateBoard(board);
	}
}
