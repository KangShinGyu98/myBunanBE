package com.cuttingEdge.bunan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private String lyricWriter;
    private String postWriter;
    private String remixArtist;
    private String videoId;
    private Long likes;
    private Long views;
    private String country;
    private String genre;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<Lyric> lyrics;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<Likey> likeys;

    private LocalDate released;
    private LocalDate posted;
    private LocalDate modified;
    private LocalDate deleted;
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
    public void update(String title,String singer, String songWriter,String lyricWriter,String remixArtist,LocalDate released,String videoId,String country,String genre){
        this.title = title;
        this.singer = singer;
        this.songWriter = songWriter;
        this.lyricWriter = lyricWriter;
        this.remixArtist = remixArtist;
        this.released = released;
        this.videoId = videoId;
        this.country = country;
        this.genre = genre;


    }

}