package Job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Job.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
