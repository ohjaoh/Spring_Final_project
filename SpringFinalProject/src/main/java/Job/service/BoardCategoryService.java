package Job.service;

import java.util.List;

import Job.entity.BoardCategory;
import Job.entity.LoginInfo;

public interface BoardCategoryService {
	public void insertBoardCategory(String categoryName, LoginInfo loginfo);

	public List<BoardCategory> BoardCategoryList();

	public void updateBoardCategory(int categoryNo, String updateCategoryName, LoginInfo loginfo);

	public void deleteBoardCategory(int categoryNo, LoginInfo loginfo);
	public BoardCategory findBoardCategory(String boardCategoryName);
}
