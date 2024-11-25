


package Job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.entity.BoardCategory;
import Job.repository.BoardCategoryRepository;

@Service
public class BoardCategoryServiceImpl implements BoardCategoryService {

	@Autowired
	private BoardCategoryRepository boardCategoryRepo;

	// 관리자가 boardCategoryRepo를 이용해서 카테고리를 등수삭 하는 메서드
	// 카테고리 등록하는 메서드
	@Override
	public void insertBoardCategory(String categoryName) {
		BoardCategory boardCategory = new BoardCategory();
		boardCategory.setCategoryName(categoryName);
		boardCategoryRepo.save(boardCategory);
	}

	// 목록읽어오는 메서드
	@Override
	public List<BoardCategory> BoardCategoryList() {
		List<BoardCategory> btList = boardCategoryRepo.findAll();
		return btList;
	}

	// 수정하는 메서드
	@Override
	public void updateBoardCategory(int categoryNo, String updateCategoryName) {
		BoardCategory findBoardCategory = boardCategoryRepo.findById(categoryNo)
				.orElseThrow(() -> new RuntimeException("BoardCategory not found  Id:" + categoryNo));

		// 필드를 수정하는 과정에서 자동으로 업데이트쿼리가 샐행된다.
		findBoardCategory.setCategoryName(updateCategoryName);

		// 하지만 혹시 몰라서 명시적으로 수정
		boardCategoryRepo.save(findBoardCategory);
	}

	// 삭제하는 메서드
	@Override
	public void deleteBoardCategory(int categoryNo) {
		BoardCategory findBoardCategory = boardCategoryRepo.findById(categoryNo)
				.orElseThrow(() -> new RuntimeException("BoardCategory not found  Id:" + categoryNo));

		boardCategoryRepo.deleteById(categoryNo);

	}
}
