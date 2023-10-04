package com.CuttingEdge.bunan.Dto;

import com.CuttingEdge.bunan.Entity.Lyric;
import com.CuttingEdge.bunan.Entity.LyricComment;

import java.util.Date;
import java.util.List;


public record LirycsResDto(
        Long id,
        String content,
        Integer order,
        List<LyricComment> lyricComments
) {
}

