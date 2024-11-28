package Job.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.controller.PasswordHashingUtils;
import Job.entity.LoginInfo;
import Job.entity.User;
import Job.entity.UserLiteInfo;
import Job.exception.ServiceErrorCode;
import Job.exception.TotalServiceException;
import Job.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	// 회원가입
	@Override
	public void registerUser(User user) {
		User alreadyCheck = userRepo.findByUserId(user.getUserId());
		// 아이디 중복 체크(서버)
		if (alreadyCheck != null) {
			throw new TotalServiceException("이미 등록존재하는 아이디입니다.", ServiceErrorCode.USER_ALREADY_EXISTS);
		}

		// 랜덤 Salt 생성
		String salt = PasswordHashingUtils.generateRandomSalt();

		// 비밀번호 해싱
		String hashedPassword = PasswordHashingUtils.hashPassword(user.getUserPassword(), salt);

		// User 엔티티에 해싱된 비밀번호와 Salt 설정
		user.setUserPassword(hashedPassword);
		user.setUserSalt(salt);

		System.out.println(user);
		// 데이터베이스에 저장
		userRepo.save(user);
	}

	// 회원정보 수정
	// 수정된 데이터를 User 객체로 받아서 처리
	@Override
	public void userUpdate(User user) {
		// 랜덤 Salt 생성
		String salt = PasswordHashingUtils.generateRandomSalt();

		// 비밀번호 해싱(암호화)
		String hashedPassword = PasswordHashingUtils.hashPassword(user.getUserPassword(), salt);

		// User 엔티티에 해싱된 비밀번호와 Salt 설정
		user.setUserPassword(hashedPassword);
		user.setUserSalt(salt);

		LocalDateTime current = LocalDateTime.now();

		// 가입한 시간에 수정시간을 수정
		user.setUpdatedAt(current);

		userRepo.save(user);
	}

	// 회원목록
	@Override
	public List<UserLiteInfo> userList() {

		List<UserLiteInfo> userList = userRepo.findAllBy();

		return userList;
	}

	// 회원조회
	@Override
	public User userInfo(LoginInfo loginInfo) {
		User user = new User();
		// 로그인 정보를 기반으로 user 객체를 찾아오기
		String userId = loginInfo.getLoginId();
		user = userRepo.findByUserId(userId);

		if (user != null) {
			user.setUserPassword(null); // 민감한 정보 제거
			user.setUserSalt(null);
		}
		// 민감한 정보는 null로 초기화 후 사용
//		user.setUserPassword("");
//		user.setUserSalt("");

		return user;
	}

	// 회원 삭제는 업데이트로 상태를 수정하는 방식
	@Override
	public void userDelete(String userId) {
		User user = new User();
		String deletedState = "Deleted";
		user = userRepo.findByUserId(userId);
		// 회원의 상태를 삭제됨으로 변경
		user.setUserState(deletedState);
		// 변경된 상태를 저장
		userRepo.save(user);
	}

	// 유저정보 불러오는 녀석
	@Override
	public User getUser(String userId) {
		User user = new User();
		if (user != null) {
			user.setUserPassword(null); // 민감한 정보 제거
			user.setUserSalt(null);
		}
		user = userRepo.findByUserId(userId);
		return user;

	}

	// Id 중복체크
	@Override
	public boolean idCheck(String userId) {
		if (userRepo.findByUserId(userId) != null) {
			return false;
		}

		return true;
	}

}
