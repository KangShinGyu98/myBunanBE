package com.cuttingEdge.bunan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "LYRIC_COMMENT")
public class LyricComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Integer likes;
    private Integer dislikes;
    private Integer reports;
    private String writer;

    private Date created;
    private Date modified;
    private Date deleted;

    @ManyToOne
    @JoinColumn(name = "lyric_id")
    private Lyric lyric;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public void setNewLyricComment(Lyric lyric, String content, String writer) {
        this.content = content;
        this.likes = 0;
        this.dislikes = 0;
        this.reports = 0;
        this.writer = writer;
        this.created = new Date();
        this.modified = new Date();
        this.deleted = null;
        this.lyric = lyric;
    }


}


