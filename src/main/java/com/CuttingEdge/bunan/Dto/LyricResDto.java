package com.CuttingEdge.bunan.Dto;

import com.CuttingEdge.bunan.Entity.LyricComment;

import java.util.List;


public record LyricResDto(
        Long id,
        String content,
        Integer order,
        List<LyricComment> lyricComments
) {
}

