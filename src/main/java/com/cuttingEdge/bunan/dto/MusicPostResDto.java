package com.cuttingEdge.bunan.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record MusicPostResDto(
        Long id,
        String title,
        String singer,
        String songWriter,
        String postWriter,
        String lyricWriter,
        String remixArtist,
        LocalDate released,
        LocalDate posted,
        LocalDate modified,
        LocalDate deleted,
        String videoId,
        Long likes,
        Long views,
        String country,
        String genre,
        List<String> tags

        ,Boolean likey

){
}
