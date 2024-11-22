package Job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.repository.BoardTagRepository;

@Service
public class BoardTagServiceImpl implements BoardTagService{

	@Autowired
	private BoardTagRepository boardTagRepo;
}
