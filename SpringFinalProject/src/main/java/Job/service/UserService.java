package Job.service;

import Job.entity.User;

public interface UserService {
    public boolean userLogin(String inputId, String inputPassword);
    public String LoginUserName(String inputUserId);
}