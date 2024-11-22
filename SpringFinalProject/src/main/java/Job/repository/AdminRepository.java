package Job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Job.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	// id를 이용하여 관리자정보를 찾는 메서드
	Admin findByAdminId(String adminId);
	
}
