package com.lifegraph.team20.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "parent_graphs")
public class Parent {
    @Id
//    オートインクリーメント的なもの
//    @GeneratedValue
    private int id;

//    @Column(nullable = false)
    private int user_id;

//    @Column(nullable = false)
    private Timestamp created_at;
//
////    @Column(nullable = false)
    private Timestamp updated_at;

}
