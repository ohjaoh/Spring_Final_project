package Job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Job.entity.User;
import Job.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestParam String username, 
                           @RequestParam String password, 
                           @RequestParam String email,
                           @RequestParam Integer age, 
                           @RequestParam String temp) {
        return userService.createUser(username, password, email, age, temp);
    }
}
