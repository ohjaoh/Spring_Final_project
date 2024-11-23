package Job.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.controller.PasswordHashingUtils;
import Job.entity.User;
import Job.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    
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
 	
	// 로그인 성공 시 회원 이름
	@Override
	public String LoginUserName(String inputUserId) {
		User user = userRepo.findByUserId(inputUserId);
		String loginUserName = user.getUserName();
		return loginUserName;
	}
}