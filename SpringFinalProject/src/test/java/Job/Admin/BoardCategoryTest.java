package Job.Admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Job.entity.LoginInfo;
import Job.service.BoardCategoryService;

@SpringBootTest
public class BoardCategoryTest {

	@Autowired
	private BoardCategoryService boardCategoryService;

	// 카테고리 추가 test
//	@Test
	public void insrtBoardCategory() {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setLoginId("testAdmin");
		String categoryName1 = "구인";
		String categoryName2 = "구직";
		String categoryName3 = "자유";
		boardCategoryService.insertBoardCategory(categoryName1, loginInfo);
		boardCategoryService.insertBoardCategory(categoryName2, loginInfo);
		boardCategoryService.insertBoardCategory(categoryName3, loginInfo);
	}

	// 카테고리 수정 test
//	@Test
	public void updateBoardCategory() {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setLoginId("testAdmin");
		String updateCategoryName = "업데이트할 카테고리이름";
		boardCategoryService.updateBoardCategory(1, updateCategoryName, loginInfo);
	}

	// 카테고리 삭제 test
//	@Test
	public void deleteBoardCategory() {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setLoginId("testAdmin");
		boardCategoryService.deleteBoardCategory(6, loginInfo);
	}
}
