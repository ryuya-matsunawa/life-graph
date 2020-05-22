package com.lifegraph.team20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifegraph.team20.models.Parent;

public interface ParentRepository extends JpaRepository<Parent, Integer> {
}
