package Job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import Job.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/jobOfferBoardList")
    public String getjobOfferBoardList() {
        return "thymeleaf/fragments/jobOfferBoardList"; //
    }

    @GetMapping("/jobSearchBoardList")
    public String getjobSearchBoardList() {
        return "thymeleaf/fragments/jobSearchBoardList"; //
    }
}
