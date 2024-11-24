package Job.controller;

import Job.entity.User;
import Job.service.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    
    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("username", null);  // 세션에서 사용자 이름 삭제
        return "redirect:/index";  // 로그아웃 후 메인 페이지로 리다이렉트
    }
}


