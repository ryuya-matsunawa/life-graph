package com.lifegraph.team20.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

//	↓梶＞setGraphでアラーが出るので、一回コメントアウト

////    人生グラフ参照API
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
////    Entityクラスとは、dbで扱うデータをアプリケーションで保持するための入れ物のようなものです。
////    value = "/{id}"のidがInteger idに入る
//	public ResponseEntity<List<LifeGraph>> graph(@PathVariable("id") Integer id) {
////    	下のidを引数として渡す（上のInteger idのこと）
////    	setGraph(id);このメソッドは何？これを起動すると、Listを作る
//		List<LifeGraph> graph = setGraph(id);
//		return ResponseEntity.ok(graph);
//	}
//
//	@Autowired
//	//MySQLのデータを持ってくるライブラリ
//	private NamedParameterJdbcTemplate jdbcTemplate;
//
//	private List<LifeGraph> setGraph(Integer id) {
////		子グラフの中のparent_id, age, score, commentをparent_id = :idで照らし合わせて作る。
////		まず、SQLに詰め込んどくイメージ
//		final String sql = "SELECT parent_id, age, score, comment FROM child_graphs WHERE parent_id = :id";
////		 addValueを使うことで上のSQLの:idに下の右のidを入れている
////		param:idに数字を入れる役割、
//		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
////		実行そのものはjdbcTemplate.queryでしてる。RowMapperは呪文
//		List<LifeGraph> result = jdbcTemplate.query(sql, param, new RowMapper<LifeGraph>() {
////			RowMappers使ったらmapRow使う呪文
//			public LifeGraph mapRow(ResultSet rs, int rowNum) throws SQLException{
////				LifeGraphに定義してある、コンストラクタに入ってるものを呼び出してくる。そして、それをresultに入れてる
//				return new LifeGraph(rs.getInt("parent_id"), rs.getInt("age"), rs.getInt("score"),rs.getString("comment"));
//			}
////			resultの定義終了
//		});
//		return result;
//	}
//

//	↑梶＞setGraphでアラートが出るので、一回コメントアウト

//	登録編集画面の：斎藤さん
	@Autowired
	LifeGraphsService service;


		@RequestMapping(value = "/new", method = RequestMethod.POST)

		  public ResponseEntity<String> postContent(@Valid @RequestBody ChildGraphData data) {

//		    long id = data.getUserId();
//		    int ageInt = data.getAge();
//		    int scoreInt = data.getScore();
//		    String comment = data.getComment();

		 // サービスを呼ぶ
			  service.resister(data);

		    return ResponseEntity.ok("OK");
		  }

//	【登録編集API】

//	＜仕様書のURI＞
//	人生グラフ登録(編集)API
//	モック作成
//	登録：{画面URL}/life-graph/new
//	編集：{画面URL}/life-graph/edit/{id}
//	人生グラフ登録(編集)API
//	POST：{API URL}/life-graphs
//	人生グラフ参照API
//	GET：{API URL}/life-graphs/{id}


//    @RequestMapping(value = "/sample", method = RequestMethod.GET)
//	public List<UserData> userData() {
//		List<UserData> userDatas = setUserData();
//		return userDatas;
//	}
//​
//	private List<UserData> setUserData() {
//		final String sql = "select * from users inner join parent_graphs on users.id = parent_graphs.user_id";
//		return jdbcTemplate.query(sql, new RowMapper<UserData>() {
//			public UserData mapRow(ResultSet rs, int rowNum) throws SQLException{
//				return new UserData(rs.getInt("id"), rs.getString("username"), rs.getTimestamp("updated_at"));
//			}
//		});
//	}
//}

	@Autowired
	LifeGraphsRepository lifeGraphsRepository;


	@RequestMapping(value = "/search", method = RequestMethod.GET)
		public List<UserData> userData() {
			List<UserData> userDatas = lifeGraphsRepository.setUserData();
			return userDatas;
		}
    //人生グラフ参照API
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //Entityクラスとは、dbで扱うデータをアプリケーションで保持するための入れ物のようなものです。
    //value = "/{id}"のidがInteger idに入る
	public ResponseEntity<List<LifeGraph>> graph(@PathVariable("id") Integer id) {
    	//下のidを引数として渡す（上のInteger idのこと）
    	//setGraph(id);このメソッドは何？これを起動すると、Listを作る
		List<LifeGraph> graph = lifeGraphsRepository.setGraph(id);
		return ResponseEntity.ok(graph);
	}
}
