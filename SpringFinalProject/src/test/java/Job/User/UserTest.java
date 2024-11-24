package Job.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Job.entity.User;
import Job.service.UserServiceImpl;

@SpringBootTest
public class UserTest {
	@Autowired
	private UserServiceImpl userServiceimpl;
	
	// 회원가입 test
	@Test
	public void resiterUserTest() {
		 User user1 = new User();
		 user1.setUserId("testUser");
		 user1.setUserAge(22);
		 user1.setUserName("거북이");
		 user1.setUserEmail("test@test.com");
		 user1.setUserPassword("testUser");
		 userServiceimpl.registerUser(user1);
	}

}
