package com.CuttingEdge.bunan.Dto;

import com.CuttingEdge.bunan.Entity.Tag;

import java.util.Date;
import java.util.List;

public record MusicListDto (
        Long id,
        String name,
        String singer,
        String writer,
        Date released,
        Date posted,
        Date modified,
        Date deleted,
        String videoId,
        Long likes,
        Long views,
        String country,
        String genre,
        List<String> tags

){
}
