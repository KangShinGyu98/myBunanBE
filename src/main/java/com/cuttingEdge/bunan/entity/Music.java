package com.cuttingEdge.bunan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String singer;
    private String songWriter;
    private String videoId;
    private Long likes;
    private Long views;
    private String country;
    private String genre;
    private String postWriter;

    private Date released;
    private Date posted;
    private Date modified;
    private Date deleted;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    // 생성자, getter 및 setter

    public void likes(){
        this.likes++;
    }
    public void disLikes(){
        this.likes--;
    }

}