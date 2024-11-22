package Job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.repository.BoardRepository;
import Job.repository.BoardTagRepository;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private BoardTagRepository boardTagRepo;
	
	// 게시판 등수삭 하는 함수
	// 게시판 목록 불러오는 함수
	
}
