package com.CuttingEdge.bunan.service;

import com.CuttingEdge.bunan.Entity.Lyric;
import com.CuttingEdge.bunan.Entity.LyricComment;
import com.CuttingEdge.bunan.Repository.LyricCommentRepository;
import com.CuttingEdge.bunan.Repository.LyricRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class LyricService {
    private final LyricRepository lyricRepository;
    private final LyricCommentRepository lyricCommentRepository;
    public LyricComment saveLyricComment( Long lyricId,String content, String writer) {

        LyricComment lyricComment = new LyricComment();
        Lyric lyric = lyricRepository.findById(lyricId).orElseThrow(()->new IllegalArgumentException("해당 가사가 없습니다."));
        log.info("lyric : " + lyric);
        lyricComment.setNewLyricComment( lyric, content, writer);
        lyricCommentRepository.save(lyricComment);
        return lyricComment;
    }

}
