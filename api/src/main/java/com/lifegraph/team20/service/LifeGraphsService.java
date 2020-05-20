package com.lifegraph.team20.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifegraph.team20.models.ChildGraphData;
import com.lifegraph.team20.models.ParentGraphData;
import com.lifegraph.team20.repository.ChildGraphRepository;
import com.lifegraph.team20.repository.ParentGraphRepository;



@Service
public class LifeGraphsService {

  @Autowired
  private ParentGraphRepository parentRepository;
  @Autowired
  private ChildGraphRepository childRepository;

  /**
   * 登録・編集のメイン処理
   *
   * @param data 登録・編集リクエスト
   */
  public void resister(ChildGraphData data) {
    // parent table
    long parentId = resisterParent(data.getParentId());
    // child table
    registerChild(parentId, data);
  }

  /**
   * 親グラフの登録・更新を行う
   *
   * @param userId ユーザーID
   * @return 親グラフID
   */
  private long resisterParent(long userId) {
    // exists parent by userId
    long parentId;
//    ここで使う前にさいばんされているかどうかを見なくてはいけない。
//    変更点：userIdは常に必須で、リクエストで送られないとき＝parentIdはnullの可能性がある。グラフがないとき。
//    ないときはインサーとする。あるときは、timestampとかでアップデートしてあげる。
    Optional<ParentGraphData> parent = parentRepository.findByUserId(userId);
//    〜〜が存在しているか、値が入ってるか知りたい
//    if (parentRepository.existsByUserId(userId)) {
    if (parent.isPresent()) {
      // update
      parentId = parent.get().getUserId();
      // 更新日時をアップデートする

    } else {
      // insert
      parentId = parentRepository.insert(userId);
    }
    return parentId;
  }

  private void registerChild(long parentId, ChildGraphData data) {
    // exists parent by userId
	  // 子供のIDがあれば、更新する
    if (childRepository.existsByUserIdAndAge(parentId, data.getAge())) {
    	// 受け取った　parentIdとageを確認するためにselect文を使って確認する
      // update
    	childRepository.updateChild(parentId, data.getAge(),data.getScore(), data.getComment());
    	// 更新した時にすでにageが存在する場合、エラーを出す
    		// エラーステータス409が出るかも？？
    		// でプリケイどエラーを調べる

    } else {

      // insert
    	childRepository.addChild(parentId, data.getAge(), data.getScore(), data.getComment());
    	// 更新した時にすでにageが存在する場合、エラーを出す
    }
  }
}