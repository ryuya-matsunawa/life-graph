//梶｜作った気がする。いらない。

package com.lifegraph.team20.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "child_graphs")
public class Child {
    @Id
//    オートインクリーメント的なもの
//    @GeneratedValue
    private int id;

//    @Column(nullable = false)
    private int parentId;

//    @Column(nullable = false)
    private int age;

//    @Column(nullable = false)
    private int score;

//    @Column(nullable = ture)
    private String comment;

}
