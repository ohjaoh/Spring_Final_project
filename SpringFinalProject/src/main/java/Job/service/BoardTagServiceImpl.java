package Job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.repository.BoardTagRepository;

@Service
public class BoardTagServiceImpl implements BoardTagService{

	@Autowired
	private BoardTagRepository boardTagRepo;

	// 관리자가 boardTagRepo를 이용해서 태그를 등수삭 하는 함수
}
