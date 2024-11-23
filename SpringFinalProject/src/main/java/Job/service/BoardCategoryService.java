package Job.service;

import java.util.List;

import Job.entity.BoardCategory;

public interface BoardCategoryService {
	public void insertBoardCategory(String CategoryName);

	public List<BoardCategory> BoardCategoryList();

	public void updateBoardCategory(int categoryNo, String updateCategoryName);

	public void deleteBoardCategory(int categoryNo);
}
