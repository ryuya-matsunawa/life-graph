package com.lifegraph.team20.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.LifeGraph;
import com.lifegraph.team20.models.UserData;
import com.lifegraph.team20.repository.LifeGraphsRepository;
import com.lifegraph.team20.service.ChildService;
import com.lifegraph.team20.service.ParentService;

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

//    削除API
    @Autowired
    private ChildService childService;
    @Autowired
    private ParentService parentService;

//  ToDo サービス名を適当なものに変更
    @DeleteMapping("/{parentId}")
    void deleteContent(@PathVariable("parentId") Integer parentId) {
//    	子テーブルを消すメソッド
    	childService.deleteContent(parentId);
//    	親テーブルを消すメソッド
    	parentService.deleteContent(parentId);
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
