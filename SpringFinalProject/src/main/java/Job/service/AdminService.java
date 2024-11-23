package Job.service;

import Job.entity.Admin;

public interface AdminService {
	
	public void registerAdmin(Admin admin);
	public boolean AdminLogin(String inputAdminId, String inputPassword);
	public String LoginAdminName(String inputAdminId);
}
