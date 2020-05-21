package com.lifegraph.team20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifegraph.team20.models.Child;

public interface ChildRepository extends JpaRepository<Child, Integer> {

	long deleteByParentId(Integer parentId);

}
