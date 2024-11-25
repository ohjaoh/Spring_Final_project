package Job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.controller.PasswordHashingUtils;
import Job.entity.Admin;
import Job.entity.LoginInfo;
import Job.exception.ServiceErrorCode;
import Job.exception.TotalServiceException;
import Job.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepo;

	// 관리자 가입 메서드 (비밀번호는 해시하여 저장)
	@Override
	public void registerAdmin(Admin admin) {
		Admin alreadyCheck = adminRepo.findByAdminId(admin.getAdminId());

		// 관리자가 이미 존재하는지 체크 아마 실제로 사용할 일은 없을
		if (alreadyCheck != null) {
			throw new TotalServiceException("이미 등록된 관리자입니다.", ServiceErrorCode.ADMIN_ALREADY_EXISTS);
		}

		// 랜덤 Salt 생성
		String salt = PasswordHashingUtils.generateRandomSalt();

		// 비밀번호 해싱
		String hashedPassword = PasswordHashingUtils.hashPassword(admin.getAdminPassword(), salt);

		// Admin 엔티티에 해싱된 비밀번호와 Salt 설정
		admin.setAdminPassword(hashedPassword);
		admin.setAdminSalt(salt);

		// 데이터베이스에 저장
		adminRepo.save(admin);
	}

	// 관리자 여부 체크
	@Override
	public boolean checkAdmin(LoginInfo loginInfo) {
		List<Admin> adminList = adminRepo.findAll();
		Boolean isAdmin = false;

		// 관리자전체에서 관리자이름인지 검색
		for (int i = 0; i < adminList.size(); i++) {
			Admin admin = adminList.get(i);
			if (admin.getAdminId().equals(loginInfo.getLoginId())) {
				isAdmin = true;
				System.out.println("관리자가 맞습니다.");
				break; // 관리자를 찾았으니 더 이상 반복할 필요 없음
			}
		}

		return isAdmin;
	}

}
