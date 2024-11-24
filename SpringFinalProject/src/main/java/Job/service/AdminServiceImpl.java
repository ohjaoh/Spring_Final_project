package Job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.controller.PasswordHashingUtils;
import Job.entity.Admin;
import Job.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepo;

	// 관리자 가입 메서드 (비밀번호는 해시하여 저장)
	@Override
	public void registerAdmin(Admin admin) {
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

}
