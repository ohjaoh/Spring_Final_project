package Job.Admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Job.service.BoardCategoryService;

@SpringBootTest
public class BoardCategoryTest {

	@Autowired
	private BoardCategoryService boardCategoryService;

//	@Test
	public void insrtBoardCategory() {
		String categoryName = "자유";
		boardCategoryService.insertBoardCategory(categoryName);
	}
	
//	@Test
	public void updateBoardCategory() {
		String updateCategoryName = "새로운변경사항";
		boardCategoryService.updateBoardCategory(1, updateCategoryName);
	}
//	@Test
	public void deleteBoardCategory() {
		boardCategoryService.deleteBoardCategory(1);
	}
}
