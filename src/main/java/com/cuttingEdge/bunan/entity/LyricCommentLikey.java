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
@NoArgsConstructor
@AllArgsConstructor
public class LyricCommentLikey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created;

    @ManyToOne
    @JoinColumn(name = "lyricComment_id")
    private LyricComment lyricComment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
