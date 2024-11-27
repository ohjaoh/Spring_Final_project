package Job.service;

import java.util.List;

import Job.entity.Board;
import Job.entity.BoardCategory;
import Job.entity.LoginInfo;

public interface BoardService {
	public void insertBoard(Board board, String categoryName, LoginInfo user);
	public Board boardPage(Long boardNo);
	public void updateBoard(Board board, LoginInfo loginInfo);
	public void deleteBoard(Long boardNo, LoginInfo loginInfo);
	public List<Board> boardList(BoardCategory categoryNum);
	public List<Board> findAll();
}
