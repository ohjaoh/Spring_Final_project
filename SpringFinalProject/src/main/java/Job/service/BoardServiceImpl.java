package Job.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.repository.BoardRepository;
import Job.entity.Board;
import Job.entity.BoardCategory;
import Job.entity.LoginInfo;
import Job.repository.BoardCategoryRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private BoardCategoryRepository boardCategoryRepo;

	// 게시판 등록 로그인한 사용자의 정보를 이용
	// 카테고리이름을 String - 게시판 작성 시 라디오 버튼이나 드롭다운으로 선택한 카테고리임
	public void insertBoard(Board board, String categoryName, LoginInfo user) {
		BoardCategory boardCategory = boardCategoryRepo.findBycategoryName(categoryName);
		board.setWriterName(user.getLoginName());

		board.setCategory(boardCategory);
		boardRepo.save(board);

	}

	// 게시글 조회
	public Board boardPage(Long boardNo) {
		// 개시판 번호로 조회하고 만약 없다면 예외를 발생
		return boardRepo.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
	}

	// 수정할 데이터를 받아서 처리
	public void updateBoard(Board board) {
		boardRepo.save(board);
	}
	// 게시판 수삭 하는 함수
	// 게시판 목록 불러오는 함수

}
