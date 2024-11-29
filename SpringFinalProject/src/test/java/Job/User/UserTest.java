package Job.User;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Job.entity.User;
import Job.entity.UserLiteInfo;
import Job.service.UserServiceImpl;

@SpringBootTest
public class UserTest {
	@Autowired
	private UserServiceImpl userServiceimpl;

	// 회원가입 test
	@Test
	public void resiterUserTest() {

//		User user1 = new User();
//		user1.setUserId("testUser");
//		user1.setUserAge(22);
//		user1.setUserName("거북이");
//		user1.setUserEmail("test@test.com");
//		user1.setUserPassword("testUser");
//		userServiceimpl.registerUser(user1);

		for (int i = 1; i < 5; i++) {
			User user1 = new User();
			user1.setUserId("testUser"+ i);
			user1.setUserAge(22 + i);
			user1.setUserName("거북이" + i);
			user1.setUserEmail("test@test.com" + i);
			user1.setUserPassword("testUser" + i);
			userServiceimpl.registerUser(user1);
		}
	}

	// 회원목록
//	@Test
	public void userListTest() {
		List<UserLiteInfo> userList = userServiceimpl.userList();

		System.out.println("유저목록:");
		for (UserLiteInfo user : userList) {
			System.out.println("User ID: " + user.getUserId() + ", User Name: " + user.getUserName() + ", Email: "
					+ user.getUserEmail() + ", Age: " + user.getUserAge() + ", Created At: " + user.getCreatedAt()
					+ ", Updated At: " + user.getUpdatedAt());
		}
	}

	// 회원삭제 (회원탈퇴, 회원관리의 삭제)
//	@Test
	public void userDeleteTest() {
		String userId = "testUser2";
		userServiceimpl.userDelete(userId);

	}

//	@Test
	public void userUpdate() {
		String userId = "testUser2";
		User user = userServiceimpl.getUser(userId);
		user.setUserName("김유신");
		user.setUserAge(24);
		user.setUserEmail("kim@kim.com");
		userServiceimpl.userUpdate(user);

	}
}
