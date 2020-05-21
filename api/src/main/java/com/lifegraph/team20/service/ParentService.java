package com.lifegraph.team20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifegraph.team20.repository.ParentRepository;

@Service
@Transactional
public class ParentService {
    @Autowired
    ParentRepository parentRepository;

	public void deleteContent(Integer id) {
    	parentRepository.deleteById(id);
    }
}
