package com.lifegraph.team20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.lifegraph.team20.models.Content;
//import com.lifegraph.team20.models.Test;
import com.lifegraph.team20.repository.TestRepository;

@Service
@Transactional
public class TestService {
    @Autowired
    TestRepository testRepository;

//    public Test postContent(Content content) {
//        return testRepository.save(content);
//    }
//
//    public List<Test> getContent() {
//        return testRepository.findAll();
//    }
//    public Test updateContent(Content content) {
//        return testRepository.save(content);
//    }

	public void deleteContent(Integer id) {
    	testRepository.deleteById(id);
    }
}