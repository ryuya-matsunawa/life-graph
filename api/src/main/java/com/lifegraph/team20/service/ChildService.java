package com.lifegraph.team20.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifegraph.team20.repository.ChildRepository;

@Service
@Transactional
public class ChildService {
	 @Autowired
	    ChildRepository childRepository;

	    public void deleteContent(Integer parentId) {
	    	childRepository.deleteByParentId(parentId);
	    }
	}
