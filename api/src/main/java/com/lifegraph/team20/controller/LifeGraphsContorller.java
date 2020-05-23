package com.lifegraph.team20.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.ChildGraphData;
import com.lifegraph.team20.models.LifeGraph;
import com.lifegraph.team20.models.UserData;
import com.lifegraph.team20.repository.LifeGraphsRepository;
import com.lifegraph.team20.service.LifeGraphsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/life-graphs")
public class LifeGraphsContorller {

  // 人生グラフ登録編集API
  @Autowired
  private LifeGraphsService service;

  @PostMapping
  public ResponseEntity<String> postContent(@Valid @RequestBody ChildGraphData data) {
    // サービスを呼ぶ
    service.resister(data);
    return ResponseEntity.ok("OK");
  }

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
  private LifeGraphsService lifeGraphsService;

  @DeleteMapping("/{parentId}")
  void deleteContent(@PathVariable("parentId") Integer parentId) {
    //    	子テーブルを消すメソッド
    lifeGraphsService.deleteChild(parentId);
    //    	親テーブルを消すメソッド
    lifeGraphsService.deleteParent(parentId);
  }
  // ↑↑↑↑↑↑ここまで削除API↑↑↑↑↑↑

  //人生グラフ参照API
  @GetMapping("/{parent_id}")
  //Entityクラスとは、dbで扱うデータをアプリケーションで保持するための入れ物のようなものです。
  //value = "/{id}"のidがInteger idに入る
  public ResponseEntity<List<LifeGraph>> graph(@PathVariable("parent_id") Integer parent_id) {
    //下のidを引数として渡す（上のInteger idのこと）
    //setGraph(id);このメソッドは何？これを起動すると、Listを作る
    List<LifeGraph> graph = lifeGraphsRepository.getGraph(parent_id);
    return ResponseEntity.ok(graph);
  }
}
