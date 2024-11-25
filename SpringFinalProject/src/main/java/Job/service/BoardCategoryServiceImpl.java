package Job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.entity.BoardCategory;
import Job.entity.LoginInfo;
import Job.exception.ServiceErrorCode;
import Job.exception.TotalServiceException;
import Job.repository.BoardCategoryRepository;

@Service
public class BoardCategoryServiceImpl implements BoardCategoryService {

	@Autowired
	private BoardCategoryRepository boardCategoryRepo;

	@Autowired
	private AdminService adminService;

	// 관리자가 boardCategoryRepo를 이용해서 카테고리를 등수삭 하는 메서드
	// 카테고리 등록하는 메서드
	@Override
	public void insertBoardCategory(String categoryName, LoginInfo loginfo) {
		BoardCategory boardCategory = new BoardCategory();

		if (boardCategoryRepo.findBycategoryName(categoryName) != null) {
			throw new TotalServiceException("이미 존재하는 카테고리입니다.", ServiceErrorCode.BOARDCATEGORY_ALREADY_EXISTS);
		}

		boardCategory.setCategoryName(categoryName);
		if (adminService.checkAdmin(loginfo)) {
			boardCategoryRepo.save(boardCategory);
			return;
		}
		System.out.println("관리자가 아닙니다.");

	}

	// 목록읽어오는 메서드
	@Override
	public List<BoardCategory> BoardCategoryList() {
		List<BoardCategory> btList = boardCategoryRepo.findAll();
		return btList;
	}

	// 수정하는 메서드
	@Override
	public void updateBoardCategory(int categoryNo, String updateCategoryName, LoginInfo loginfo) {
		BoardCategory findBoardCategory = boardCategoryRepo.findById(categoryNo)
				.orElseThrow(() -> new TotalServiceException(
						"존재하지 않는 카테고리입니다. " + ServiceErrorCode.BOARDCATEGORY_NOT_FOUND.getCode(),
						ServiceErrorCode.BOARDCATEGORY_NOT_FOUND));
		// 관리자 여부 체크
		if (adminService.checkAdmin(loginfo)) {
			// 필드를 수정하는 과정에서 자동으로 업데이트쿼리가 샐행된다.
			findBoardCategory.setCategoryName(updateCategoryName);

			// 하지만 혹시 몰라서 명시적으로 수정
			boardCategoryRepo.save(findBoardCategory);
			return;
		}
		System.out.println("관리자가 아닙니다.");

	}

	// 삭제하는 메서드
	@Override
	public void deleteBoardCategory(int categoryNo, LoginInfo loginfo) {
		if (boardCategoryRepo.findById(categoryNo) == null) {
			throw new TotalServiceException("존재하지 않는 카테고리입니다.", ServiceErrorCode.BOARDCATEGORY_NOT_FOUND);
		}
		// 관리자 여부 체크
		if (adminService.checkAdmin(loginfo)) {
			// 카테고리 삭제
			boardCategoryRepo.deleteById(categoryNo);
			return;
		}
		System.out.println("관리자가 아닙니다.");

	}
}
