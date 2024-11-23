package Job.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Job.entity.User;
import Job.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    public User getUser(User user) {
        Optional<User> findUser = userRepo.findByEmail(user.getEmail());
        if (findUser.isPresent()) {
            return findUser.get();
        } else {
            return null;
        }
    }
}