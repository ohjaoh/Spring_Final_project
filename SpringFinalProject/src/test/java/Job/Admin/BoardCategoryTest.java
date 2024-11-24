package Job.Admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Job.service.BoardCategoryService;

@SpringBootTest
public class BoardCategoryTest {

	@Autowired
	private BoardCategoryService boardCategoryService;

	// 카테고리 추가 test
	@Test
	public void insrtBoardCategory() {
		String categoryName1 = "구인";
		String categoryName2 = "구직";
		String categoryName3 = "자유";
		boardCategoryService.insertBoardCategory(categoryName1);
		boardCategoryService.insertBoardCategory(categoryName2);
		boardCategoryService.insertBoardCategory(categoryName3);
	}

	// 카테고리 수정 test
//	@Test
	public void updateBoardCategory() {
		String updateCategoryName = "업데이트할 카테고리이름";
		boardCategoryService.updateBoardCategory(1, updateCategoryName);
	}

	// 카테고리 삭제 test
//	@Test
	public void deleteBoardCategory() {
		boardCategoryService.deleteBoardCategory(1);
	}
}
