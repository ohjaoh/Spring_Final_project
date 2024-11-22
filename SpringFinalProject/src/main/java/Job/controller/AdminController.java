package Job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Job.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin")
    public String adminPage(Model model) {
        // 예제 데이터 실제로는 tag목록을 뽑아서 넣는 형태로 해야함
        List<String> boardList = List.of("게시판1", "게시판2", "게시판3", "게시판4", "게시판5");
        model.addAttribute("boardList", boardList);
        return "admin/Admin";
    }

}
