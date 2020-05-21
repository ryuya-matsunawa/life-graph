package com.lifegraph.team20.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.ChildGraphData;
import com.lifegraph.team20.models.LifeGraph;
import com.lifegraph.team20.service.LifeGraphsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/life-graphs")
public class LifeGraphsContorller {
	//@RequestMapping(value = "/search", method = RequestMethod.GET)
	//	public List<UserData> userData() {
	//		List<UserData> userDatas = setUserData();
	//		return userDatas;
	//	}
	//
	//	@Autowired
	//
	//	private JdbcTemplate jdbcTemplate;
	//
	//	private List<UserData> setUserData() {
	//		final String sql = "select * from users inner join parent_graphs on users.id = parent_graphs.user_id";
	//		return jdbcTemplate.query(sql, new RowMapper<UserData>() {
	//			public UserData mapRow(ResultSet rs, int rowNum) throws SQLException{
	//				return new UserData(rs.getInt("id"), rs.getString("username"), rs.getTimestamp("updated_at"));
	//			}
	//		});
	//	}

	//    人生グラフ参照API
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	//    Entityクラスとは、dbで扱うデータをアプリケーションで保持するための入れ物のようなものです。
	//    value = "/{id}"のidがInteger idに入る
	public ResponseEntity<List<LifeGraph>> graph1(@PathVariable("id") Integer id) {
		//    	下のidを引数として渡す（上のInteger idのこと）
		//    	setGraph(id);このメソッドは何？これを起動すると、Listを作る
		List<LifeGraph> graph = setGraph(id);
		return ResponseEntity.ok(graph);
	}

	@Autowired
	//MySQLのデータを持ってくるライブラリ
	private NamedParameterJdbcTemplate jdbcTemplate;

	private List<LifeGraph> setGraph(Integer id) {
		//		子グラフの中のparent_id, age, score, commentをparent_id = :idで照らし合わせて作る。
		//		まず、SQLに詰め込んどくイメージ
		final String sql = "SELECT parent_id, age, score, comment FROM child_graphs WHERE parent_id = :id";
		//		 addValueを使うことで上のSQLの:idに下の右のidを入れている
		//		param:idに数字を入れる役割、
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		//		実行そのものはjdbcTemplate.queryでしてる。RowMapperは呪文
		List<LifeGraph> result = jdbcTemplate.query(sql, param, new RowMapper<LifeGraph>() {
			//			RowMappers使ったらmapRow使う呪文
			public LifeGraph mapRow(ResultSet rs, int rowNum) throws SQLException {
				//				LifeGraphに定義してある、コンストラクタに入ってるものを呼び出してくる。そして、それをresultに入れてる
				return new LifeGraph(rs.getInt("parent_id"), rs.getInt("age"), rs.getInt("score"),
						rs.getString("comment"));
			}
			//			resultの定義終了
		});
		return result;
	}

	// 人生グラフ登録編集API
	@Autowired
	LifeGraphsService service;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<String> postContent(@Valid @RequestBody ChildGraphData data) {
//		レスポンスにグラフのデータを載せるときは使う
//		List<LifeGraph> graph = setEditedGraph(data.getUserId());
		// サービスを呼ぶ
		service.resister(data);
		return ResponseEntity.ok("OK");
	}
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
//
//	private LifeGraphsRepository lifeGraphsRepository;
//
//	// 検索API
//	@GetMapping
//	public ResponseEntity<List<UserData>> userData() {
//		List<UserData> userDatas = lifeGraphsRepository.getUserData();
//		return ResponseEntity.ok(userDatas);
//	}
//	// ↑↑↑↑↑↑ここまで検索API↑↑↑↑↑↑
//
//	@Autowired
//	LifeGraphsRepository lifeGraphsRepository;
//
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public List<UserData> userData() {
//		List<UserData> userDatas = lifeGraphsRepository.setUserData();
//		return userDatas;
//	}
//
//	//人生グラフ参照API
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	//Entityクラスとは、dbで扱うデータをアプリケーションで保持するための入れ物のようなものです。
//	//value = "/{id}"のidがInteger idに入る
//	public ResponseEntity<List<LifeGraph>> graph(@PathVariable("id") Integer id) {
//		//下のidを引数として渡す（上のInteger idのこと）
//		//setGraph(id);このメソッドは何？これを起動すると、Listを作る
//		List<LifeGraph> graph = lifeGraphsRepository.setGraph(id);
//
//	private ContentService contentService;
//
//	@PostMapping
//	Content postContent(@RequestBody Content content) {
//		return contentService.postContent(content);
//	}
//
//	@RequestMapping(value = "/a", method = RequestMethod.GET)
//	List<Content> getContent() {
//		return contentService.getContent();
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	Content putTweet(@PathVariable("id") Integer id, @RequestBody Content content) {
//		content.setId(id);
//		return contentService.updateContent(content);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	void deleteContent(@PathVariable("id") Integer id) {
//		contentService.deleteContent(id);
//	}
//
//	//人生グラフ参照API
//	@GetMapping("/{id}")
//	//Entityクラスとは、dbで扱うデータをアプリケーションで保持するための入れ物のようなものです。
//	//value = "/{id}"のidがInteger idに入る
//	public ResponseEntity<List<LifeGraph>> graph(@PathVariable("id") Integer id) {
//		//下のidを引数として渡す（上のInteger idのこと）
//		//setGraph(id);このメソッドは何？これを起動すると、Listを作る
//		List<LifeGraph> graph = lifeGraphsRepository.getGraph(id);
//		return ResponseEntity.ok(graph);
//	}
}
