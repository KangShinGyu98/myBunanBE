package com.cuttingEdge.bunan.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record UpdateMusicResDto(
        String title,
        String singer,
        String songWriter,
        String postWriter,
        String lyricWriter,
        String remixArtist,
        LocalDate released,
        String videoId,
        String country,
        String genre,
        List<String> tags,
        String lyric
//        String lyricComment

) {
}
