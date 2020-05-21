package com.lifegraph.team20.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.Content;
import com.lifegraph.team20.models.LifeGraph;
import com.lifegraph.team20.models.UserData;
import com.lifegraph.team20.repository.LifeGraphsRepository;
import com.lifegraph.team20.service.ContentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/life-graphs")
public class LifeGraphsContorller {

	@Autowired
	private LifeGraphsRepository lifeGraphsRepository;

	// 検索API
	@GetMapping
		public ResponseEntity<List<UserData>> userData() {
			List<UserData> userDatas = lifeGraphsRepository.getUserData();
			return ResponseEntity.ok(userDatas);
		}
	// ↑↑↑↑↑↑ここまで検索API↑↑↑↑↑↑

	// 登録・編集API
	@Autowired
	private ContentService contentService;

	@PostMapping
    Content postContent(@RequestBody Content content) {
        return contentService.postContent(content);
    }

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    List<Content> getContent() {
        return contentService.getContent();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Content putTweet(@PathVariable("id") Integer id, @RequestBody Content content) {
    	content.setId(id);
        return contentService.updateContent(content);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteContent(@PathVariable("id") Integer id) {
    	contentService.deleteContent(id);
    }

    //人生グラフ参照API
    @GetMapping("/{id}")
    //Entityクラスとは、dbで扱うデータをアプリケーションで保持するための入れ物のようなものです。
    //value = "/{id}"のidがInteger idに入る
	public ResponseEntity<List<LifeGraph>> graph(@PathVariable("id") Integer id) {
    	//下のidを引数として渡す（上のInteger idのこと）
    	//setGraph(id);このメソッドは何？これを起動すると、Listを作る
		List<LifeGraph> graph = lifeGraphsRepository.getGraph(id);
		return ResponseEntity.ok(graph);
	}
}
