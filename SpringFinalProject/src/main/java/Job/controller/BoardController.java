package Job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import Job.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
}
