package com.lifegraph.team20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifegraph.team20.models.Content;

public interface ContentRepository extends JpaRepository<Content, Integer> {}