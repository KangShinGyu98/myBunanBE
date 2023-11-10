package com.cuttingEdge.bunan.dto;

import com.cuttingEdge.bunan.entity.LyricComment;

import java.util.Date;
import java.util.List;


public record LyricCommentResDto(
        Long id,
        String content,
        Integer likes,
        Boolean likey,
        Integer dislikes,
        String writer,
        Date created
) {
}

