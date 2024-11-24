package Job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.controller.PasswordHashingUtils;
import Job.entity.Admin;
import Job.entity.LoginInfo;
import Job.entity.User;
import Job.repository.AdminRepository;
import Job.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private UserRepository userRepo;

	// 관리자 로그인
	@Override
	public boolean AdminLogin(String inputAdminId, String inputPassword) {
		// 아이디를 조회하여 존재여부 체크
		Admin admin = adminRepo.findByAdminId(inputAdminId);
//		System.out.println("입력한 id는 무엇인가요? " + inputAdminId);
		if (admin == null) {
			return false;
		}
		// 존재하는 경우 아이디로 찾아온 관리자의 비밀번호와 솔트를 가져와 디코딩 후 입력한 비밀번호와 비교
		String getSalt = admin.getAdminSalt();
		String getPassword = admin.getAdminPassword();

		// 입력한 비밀번호를 가져온 솔트값과 합하여 해시화
		String hashedInputPasswrod = PasswordHashingUtils.hashPassword(inputPassword, getSalt);

		// 해싱된 입력한 비밀번호와 기존의 비밀번호를 비교하여 boolean형으로 반환
		return getPassword.equals(hashedInputPasswrod);
	}

	// 회원 로그인
	@Override
	public boolean userLogin(String inputId, String inputPassword) {
		// 아이디를 조회하여 존재여부 체크
		User user = userRepo.findByUserId(inputId);
		System.out.println("입력한 id는 무엇인가요? " + inputId);
		if (user == null) {
			throw new RuntimeException("존재하지 않는 관리자입니다.");
		}
		// 존재하는 경우 아이디로 찾아온 관리자의 비밀번호와 솔트를 가져와 디코딩 후 입력한 비밀번호와 비교
		String getSalt = user.getUserSalt();
		String getPassword = user.getUserPassword();

		// 입력한 비밀번호를 가져온 솔트값과 합하여 해시화
		String hashedInputPasswrod = PasswordHashingUtils.hashPassword(inputPassword, getSalt);

		// 해싱된 입력한 비밀번호와 기존의 비밀번호를 비교하여 boolean형으로 반환
		return getPassword.equals(hashedInputPasswrod);
	}

	// 로그인 성공 시 저장할 관리자 정보
	@Override
	public LoginInfo LoginAdminName(String inputAdminId) {
		LoginInfo loginInfo = new LoginInfo();
		Admin admin = adminRepo.findByAdminId(inputAdminId);
		loginInfo.setLoginName(admin.getAdminName());
		loginInfo.setLoginId(admin.getAdminId());
		return loginInfo;
	}

	// 로그인 성공 시 저장할 사용자 정보
	@Override
	public LoginInfo LoginUserName(String inputUserId) {
		LoginInfo loginInfo = new LoginInfo();
		User user = userRepo.findByUserId(inputUserId);
		loginInfo.setLoginId(user.getUserId());
		loginInfo.setLoginName(user.getUserName());

		return loginInfo;
	}

}
