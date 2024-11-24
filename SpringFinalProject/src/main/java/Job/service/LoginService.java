package Job.service;

import Job.entity.LoginInfo;

public interface LoginService {
	public boolean AdminLogin(String inputAdminId, String inputPassword);

	public boolean userLogin(String inputId, String inputPassword);

	public LoginInfo LoginAdminName(String inputAdminId);

	public LoginInfo LoginUserName(String inputUserId);

}
