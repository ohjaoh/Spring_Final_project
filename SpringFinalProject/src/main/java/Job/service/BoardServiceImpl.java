package Job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.repository.BoardRepository;
import Job.entity.Admin;
import Job.entity.Board;
import Job.entity.BoardCategory;
import Job.entity.LoginInfo;
import Job.repository.AdminRepository;
import Job.repository.BoardCategoryRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private BoardCategoryRepository boardCategoryRepo;
	@Autowired
	private AdminRepository adminRepo;

	// 게시판 등록 로그인한 사용자의 정보를 이용
	// 카테고리이름을 String - 게시판 작성 시 라디오 버튼이나 드롭다운으로 선택한 카테고리임
	public void insertBoard(Board board, String categoryName, LoginInfo user) {
		BoardCategory boardCategory = boardCategoryRepo.findBycategoryName(categoryName);

		board.setwriterId(user.getLoginId());

		board.setCategory(boardCategory);
		boardRepo.save(board);

	}

	// 게시글 조회
	public Board boardPage(Long boardNo) {
		// 개시판 번호로 조회하고 만약 없다면 예외를 발생
		return boardRepo.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
	}

	// 게시글 수정
	// 수정할 데이터를 게시판객체로 받아서
	public void updateBoard(Board board, LoginInfo loginInfo) {
		// 로그인한 사람의 게시글만 수정가능
		if (loginInfo.getLoginId().equals(board.getwriterId())) {
			boardRepo.save(board);
		}

	}

	// 게시판 삭제 하는 함수
	public void deleteBoard(Long boardNo, LoginInfo loginInfo) {
		// 게시글을 찾아서 board객체로 연결
		Board board = boardRepo.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
		List<Admin> adminList = adminRepo.findAll();
		Boolean isAdmin = false;

		// 관리자전체에서 관리자이름인지 검색
		for (int i = 0; i < adminList.size(); i++) {
			Admin admin = adminList.get(i);
			if (admin.getAdminId().equals(loginInfo.getLoginId())) {
				isAdmin = true;
				break; // 관리자를 찾았으니 더 이상 반복할 필요 없음
			}
		}

		// 로그인한 사람이 본인이거나 관리자인 경우
		if (loginInfo.getLoginId().equals(board.getwriterId()) || isAdmin) {
			// 게시글 상태를 삭제됨으로 바꾼다.
			board.setBoardState("Deleted");
			// 변경된 상태를 저장한다.
			boardRepo.save(board);

		}

	}

	// 게시판 목록 불러오는 함수
	public List<Board> boardList() {
		List<Board> boardList = boardRepo.findAll();
		return boardList;
	}

}
