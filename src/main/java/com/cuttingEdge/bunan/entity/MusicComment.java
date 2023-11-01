package com.cuttingEdge.bunan.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MusicComment {
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
    @JoinColumn(name = "music_id")
    private Music music;


}
