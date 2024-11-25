package Job.service;

import Job.entity.Admin;
import Job.entity.LoginInfo;

public interface AdminService {

	public void registerAdmin(Admin admin);

	public boolean checkAdmin(LoginInfo loginInfo);
}
