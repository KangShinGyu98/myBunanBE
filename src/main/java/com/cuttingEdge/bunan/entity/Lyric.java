package com.cuttingEdge.bunan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lyric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @OneToMany(mappedBy = "lyric", cascade = CascadeType.ALL)
    private List<LyricComment> comments;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    public void update(String content,Music music){
        this.content = content;
        this.music = music;
    }
}
