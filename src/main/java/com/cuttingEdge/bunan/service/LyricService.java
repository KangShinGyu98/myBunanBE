package com.cuttingEdge.bunan.service;

import com.cuttingEdge.bunan.entity.Lyric;
import com.cuttingEdge.bunan.entity.LyricComment;
import com.cuttingEdge.bunan.repository.LyricCommentRepository;
import com.cuttingEdge.bunan.repository.LyricRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
