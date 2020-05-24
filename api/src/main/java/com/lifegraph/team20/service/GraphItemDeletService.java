package com.lifegraph.team20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifegraph.team20.repository.GraphItemDeletRepositry;

@Service
public class GraphItemDeletService {

  @Autowired
  private GraphItemDeletRepositry graphItemDeletRepositry;

  public void deletItem(long id) {
    graphItemDeletRepositry.deletById(id);
  }

}
