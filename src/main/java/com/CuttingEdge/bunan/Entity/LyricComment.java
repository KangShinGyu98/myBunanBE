package com.CuttingEdge.bunan.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LyricComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Integer likes;
    private Integer dislikes;
    private Integer reports;
    private Integer writer;

    private Date created;
    private Date modified;
    private Date deleted;

    @ManyToOne
    @JoinColumn(name = "lyric_id")
    private Lyric lyric;



}
