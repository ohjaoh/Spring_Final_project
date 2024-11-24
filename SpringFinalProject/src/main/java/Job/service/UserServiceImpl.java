package Job.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.controller.PasswordHashingUtils;
import Job.entity.User;
import Job.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	// 회원가입
	@Override
	public void registerUser(User user) {
		// 랜덤 Salt 생성
		String salt = PasswordHashingUtils.generateRandomSalt();

		// 비밀번호 해싱
		String hashedPassword = PasswordHashingUtils.hashPassword(user.getUserPassword(), salt);

		// User 엔티티에 해싱된 비밀번호와 Salt 설정
		user.setUserPassword(hashedPassword);
		user.setUserSalt(salt);

		LocalDateTime current = LocalDateTime.now();

		// 가입한 시간에 맞게 둘을 지정
		user.setCreatedAt(current);
		user.setUpdatedAt(current);

		System.out.println(user);
		// 데이터베이스에 저장
		userRepo.save(user);
	}

	// 회원정보 수정 메서드 (입력객체는 수정된 User객체)
	public void userUpdate(User user) {
		// 랜덤 Salt 생성
		String salt = PasswordHashingUtils.generateRandomSalt();

		// 비밀번호 해싱
		String hashedPassword = PasswordHashingUtils.hashPassword(user.getUserPassword(), salt);

		// User 엔티티에 해싱된 비밀번호와 Salt 설정
		user.setUserPassword(hashedPassword);
		user.setUserSalt(salt);

		LocalDateTime current = LocalDateTime.now();

		// 가입한 시간에 수정시간을 수정
		user.setUpdatedAt(current);

		userRepo.save(user);
	}
	
	// 회원조회는 로그인한 회원의 이름과 id를 기준으로 찾는 형태로 진행

}
