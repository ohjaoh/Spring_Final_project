package Job.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.entity.User;
import Job.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
    @Autowired
	private UserRepository userRepo;
    
    @Override
    public User getUser(User users) {
    	Optional<User> findUser =userRepo.findById(users.getId());
    	
	return findUser.get();
    }
}
