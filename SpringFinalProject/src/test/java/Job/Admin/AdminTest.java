package Job.Admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Job.entity.Admin;
import Job.service.AdminServiceImpl;

@SpringBootTest
public class AdminTest {

	@Autowired
	private AdminServiceImpl adminImpl;

	// 관리자 추가 test
	@Test
	public void inertAdmin() {
		Admin admin1 = new Admin();
		admin1.setAdminId("testAdmin");
		admin1.setAdminName("김이박");
		admin1.setAdminPassword("testAdmin");
		admin1.setAdminPhoneNumber("01011112222");
		admin1.setAdminPosition("main_Admin");
		adminImpl.registerAdmin(admin1);
	}
}
