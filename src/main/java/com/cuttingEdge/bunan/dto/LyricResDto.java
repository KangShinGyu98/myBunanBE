package com.cuttingEdge.bunan.dto;

import com.cuttingEdge.bunan.entity.LyricComment;

import java.util.List;


public record LyricResDto(
        Long id,
        String content,
        Integer order,
        List<LyricComment> lyricComments
) {
}

