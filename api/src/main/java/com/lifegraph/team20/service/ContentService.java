package com.lifegraph.team20.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifegraph.team20.models.Content;
import com.lifegraph.team20.repository.ContentRepository;

@Service
@Transactional
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;

    public Content postContent(Content content) {
        return contentRepository.save(content);
    }

    public List<Content> getContent() {
        return contentRepository.findAll();
    }
    public Content updateContent(Content content) {
        return contentRepository.save(content);
    }

	public void deleteContent(Integer id) {
    	contentRepository.deleteById(id);
    }
}