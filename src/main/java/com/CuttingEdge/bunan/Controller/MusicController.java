package com.CuttingEdge.bunan.Controller;

import com.CuttingEdge.bunan.Dto.ApiDto;
import com.CuttingEdge.bunan.Dto.LyricInputReqDto;
import com.CuttingEdge.bunan.Dto.MusicListResDto;
import com.CuttingEdge.bunan.Dto.LyricResDto;
import com.CuttingEdge.bunan.Entity.LyricComment;
import com.CuttingEdge.bunan.Repository.MusicRepository;
import com.CuttingEdge.bunan.service.LyricService;
import com.CuttingEdge.bunan.service.MusicListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MusicController {

    private final MusicListService musicListService;
    private final LyricService lyricService;
    @GetMapping("/musics")
    public ApiDto<MusicListResDto> getMusics(@RequestParam(required = false) String country,
                                             @RequestParam(required = false) String genre,
                                             @RequestParam(required = false) String ordering,
                                             @RequestParam(required = false) String search,
                                             @RequestParam(required = false) List<String> tags) {

        List<MusicListResDto> results = musicListService.getMusics(country, genre, ordering, search, tags);
        ApiDto<MusicListResDto> returnDto = new ApiDto(results);
        return returnDto;// 예제로 모든 음악을 반환
    }

    @GetMapping("/videoId/{id}")
    public ApiDto<String> getVideoId(@PathVariable Long id){
        log.info("id : " + id);
        String videoId = musicListService.getVideoId(id);
        ApiDto<String> returnDto = new ApiDto(List.of(videoId));
        return returnDto;
    }
    @GetMapping("/musics/{id}")
    public ApiDto<LyricResDto> getMusicPost(@PathVariable Long id){
        log.info("id : " + id);

        List<LyricResDto> lyricsResDtos = musicListService.getLyrics(id);
        // music id에 해당하는 Lyrics 를 order 순으로 반환 하면서, Lyric에 맞는 comment 를 좋아요 순으로 반환
        ApiDto<LyricResDto> returnDto = new ApiDto(lyricsResDtos);
        return returnDto;
    }

    @PostMapping("/lyricInput")
    public LyricComment lyricInput(@RequestBody LyricInputReqDto dto){
        log.info("lyricInputReqDto : " + dto);
        LyricComment lyricComment= lyricService.saveLyricComment(dto.lyricId().longValue(), dto.content(), dto.writer());
        return lyricComment;
    }


}
