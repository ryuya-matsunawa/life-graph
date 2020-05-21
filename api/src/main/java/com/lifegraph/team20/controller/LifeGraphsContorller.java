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
import com.lifegraph.team20.service.ChildService;
import com.lifegraph.team20.service.LifeGraphsService;
import com.lifegraph.team20.service.ParentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/life-graphs")
public class LifeGraphsContorller {

	// 人生グラフ登録編集API
	@Autowired
	LifeGraphsService service;

	@PostMapping
	public ResponseEntity<String> postContent(@Valid @RequestBody ChildGraphData data) {
		//		レスポンスにグラフのデータを載せるときは使う
		//		List<LifeGraph> graph = setEditedGraph(data.getUserId());
		// サービスを呼ぶ
		service.resister(data);
		return ResponseEntity.ok("OK");
	}

	//ToDoあとで消す
	//登録編集APIの方でレスポンスを返すために使うかも
	//	private List<LifeGraph> setEditedGraph(long l) {
	//		//		子グラフの中のparent_id, age, score, commentをparent_id = :idで照らし合わせて作る。
	//		//		まず、SQLに詰め込んどくイメージ
	//		final String sql = "SELECT parent_id, age, score, comment FROM child_graphs WHERE parent_id = :id";
	//		//		 addValueを使うことで上のSQLの:idに下の右のidを入れている
	//		//		param:idに数字を入れる役割、
	//		SqlParameterSource param = new MapSqlParameterSource().addValue("id", l);
	//		//		実行そのものはjdbcTemplate.queryでしてる。RowMapperは呪文
	//		List<LifeGraph> result = jdbcTemplate.query(sql, param, new RowMapper<LifeGraph>() {
	//			//			RowMappers使ったらmapRow使う呪文
	//			public LifeGraph mapRow(ResultSet rs, int rowNum) throws SQLException {
	//				//				LifeGraphに定義してある、コンストラクタに入ってるものを呼び出してくる。そして、それをresultに入れてる
	//				return new LifeGraph(rs.getInt("parent_id"), rs.getInt("age"), rs.getInt("score"),
	//						rs.getString("comment"));
	//			}
	//			//			resultの定義終了
	//		});
	//		return result;
	//	}
	//	↑↑↑↑↑↑↑登録編集APIで使うかも

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