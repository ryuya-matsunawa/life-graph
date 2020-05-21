package com.lifegraph.team20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifegraph.team20.models.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {}