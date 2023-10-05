package com.CuttingEdge.bunan.Dto;

import java.util.Date;
import java.util.List;

public record MusicListResDto(
        Long id,
        String title,
        String singer,
        String songWriter,
        String postWriter,
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
