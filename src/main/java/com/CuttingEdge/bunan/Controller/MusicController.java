package com.CuttingEdge.bunan.Controller;

import com.CuttingEdge.bunan.Dto.ApiDto;
import com.CuttingEdge.bunan.Dto.MusicListResDto;
import com.CuttingEdge.bunan.Dto.LirycsResDto;
import com.CuttingEdge.bunan.service.MusicListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MusicController {

    private final MusicListService musicListService;

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
    public ResponseEntity<String> getVideoId(@PathVariable Long id){
        log.info("id : " + id);
        String videoId = musicListService.getVideoId(id);
        return ResponseEntity.ok().body(videoId);
    }
    @GetMapping("/musics/{id}")
    public ApiDto<LirycsResDto> getMusicPost(@PathVariable Long id){
        log.info("id : " + id);

        List<LirycsResDto> lirycsResDtos = musicListService.getLyrics(id);
        // music id에 해당하는 Lyrics 를 order 순으로 반환 하면서, Lyric에 맞는 comment 를 좋아요 순으로 반환
        ApiDto<LirycsResDto> returnDto = new ApiDto(lirycsResDtos);
        return returnDto;
    }


}
