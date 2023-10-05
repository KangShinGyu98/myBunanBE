package com.CuttingEdge.bunan.Dto;

import java.util.List;

public record CreateNewMusicReqDto(
        String title,
        String singer,
        String songWriter,
        String postWriter,
        String youtubeUrl,
        String country,
        String genre,
        List<String> tags,
        String lyric,
        String lyricComment

) {
}
