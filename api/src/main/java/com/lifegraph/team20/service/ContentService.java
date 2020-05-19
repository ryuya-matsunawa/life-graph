//package com.lifegraph.team20.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.lifegraph.team20.models.Content;
//
//@Service
//@Transactional
//public class ContentService {
//    @Autowired
//    ResponseEntity<String> contentEntity;
//
//    public ResponseEntity<String> postContent(Content content) {
//        return contentEntity.save(content);
//    }
//
//    public List<Content> getContent() {
//        return contentRepository.findAll();
//    }
//    public ResponseEntity<String> updateContent(Content content) {
//        return contentRepository.save(content);
//    }
//
//	public void deleteContent(Integer id) {
//    	contentRepository.deleteById(id);
//    }
//}