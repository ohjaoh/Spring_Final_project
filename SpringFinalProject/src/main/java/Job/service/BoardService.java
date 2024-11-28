package Job.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Job.entity.Board;
import Job.entity.BoardCategory;
import Job.entity.LoginInfo;

public interface BoardService {
	public void insertBoard(Board board, String categoryName, LoginInfo user);

	public Board boardPage(Long boardNo);

	public void updateBoard(Board board, LoginInfo loginInfo);

	public void deleteBoard(Long boardNo, LoginInfo loginInfo);

	public Page<Board> boardList(BoardCategory categoryNum, Pageable pageable);

	public Page<Board> findAll(Pageable pageable);
}
