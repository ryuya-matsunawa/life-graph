package com.lifegraph.team20.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifegraph.team20.models.ChildGraphData;
import com.lifegraph.team20.repository.ChildGraphRepository;
import com.lifegraph.team20.repository.ChildRepository;
import com.lifegraph.team20.repository.ParentGraphRepository;
import com.lifegraph.team20.repository.ParentRepository;

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
    final long parentId = resisterParent(data.getUserId());
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
    final long parentId;

    Optional<ChildGraphData> parent = parentRepository.findByUserId(userId);
    if (parent.isPresent()) {
      // update
      parentId = parent.get().getUserId();
      // 更新日時をアップデートする
      parentRepository.updateParent(userId);
    } else {
      //      parentIdをuserIdから引っ張ってくる処理がされてる。
      // insert
      parentId = parentRepository.insert(userId);
    }
    return parentId;
  }

  private void registerChild(long parentId, ChildGraphData data) {
    // exists parent by userId
    // かぶってたらtrue
    if (childRepository.existsByUserIdAndAge(data.getUserId(), data.getAge())) {
      // 受け取った　parentIdとageを確認するためにselect文を使って確認する
      // updateをUserId(pearnt_id)とageをキーにして行う
      //      被っている場所に入れてあげる。
      childRepository.updateChildByUserIdAndAge(data.getUserId(), data.getAge(), data.getScore(), data.getComment());

    } else {
      // 子idが送信されればupdateする
      //    idがnullの時＝＞{add} else {update}
      if (data.getId() == 0) {
        // insert
        //        Id以外全部つかう
        childRepository.addChild(data.getUserId(), data.getAge(), data.getScore(), data.getComment());
        // 更新した時にすでにageが存在する場合、エラーを出す

      } else {
        // updateをidをキーにして行う
        childRepository.updateChildById(data.getId(), data.getAge(), data.getScore(), data.getComment());

      }
    }
  }

  @Autowired
  private ChildRepository childGraphRepository;

  @Transactional
  public void deleteChild(Integer parentId) {
    childGraphRepository.deleteByParentId(parentId);
  }

  @Autowired
  ParentRepository parentGraphRepository;

  public void deleteParent(Integer id) {
    parentGraphRepository.deleteById(id);
  }
}