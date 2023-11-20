package com.cuttingEdge.bunan.controller;

import com.cuttingEdge.bunan.dto.*;
import com.cuttingEdge.bunan.entity.LyricComment;
import com.cuttingEdge.bunan.service.LyricService;
import com.cuttingEdge.bunan.service.MusicListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MusicController {

    private final MusicListService musicListService;
    private final LyricService lyricService;

    @GetMapping("/musics") //메인화면에서 모든 음악포스트를 반환
    public ApiDto<MusicListResDto> getMusics(@RequestParam(required = false) String country,
                                             @RequestParam(required = false) String genre,
                                             @RequestParam(required = false) String ordering,
                                             @RequestParam(required = false) String search,
                                             @RequestParam(required = false) List<String> tags,
                                             @RequestParam(required = false) String email
    ) {
//        todo email => nickname 으로 하는게 털렸을 때 덜 취약한듯?
        if (ObjectUtils.isEmpty(email)){

            List<MusicListResDto> results = musicListService.getMusics(country, genre, ordering, search, tags);
            ApiDto<MusicListResDto> returnDto = new ApiDto(results);
            return returnDto;// 예제로 모든 음악을 반환
        }else{
// 로그인 한 사용자의 경우
//            todo token 값을 통해서 validation user 랑 email 이랑 실제로 같은지 체크할 것 
            List<MusicListResDto> results = musicListService.getMusicsForUser(country, genre, ordering, search, tags,email);
            ApiDto<MusicListResDto> returnDto = new ApiDto(results);
            return returnDto;// 예제로 모든 음악을 반환
        }
    }


    @GetMapping("/music/{id}")
    public MusicPostResDto getVideoId(@PathVariable Long id){
        log.info("id : " + id);

        MusicPostResDto result = musicListService.getMusicPost(id);
        return result;
    }
    @GetMapping("/lyric/{musicId}")
    public ApiDto<LyricResDto> getMusicPost(@PathVariable Long musicId,
                                            @RequestParam(required = false) String nickname){
        log.info("getMusicPost , music id : {} nickname : {}",musicId, nickname);
        if (ObjectUtils.isEmpty(nickname)){

            List<LyricResDto> lyricsResDtos = lyricService.getLyrics(musicId);
            // music id에 해당하는 Lyrics 를 order 순으로 반환 하면서, Lyric에 맞는 comment 를 좋아요 순으로 반환
            ApiDto<LyricResDto> returnDto = new ApiDto(lyricsResDtos);
            return returnDto;

        }else{
            log.info("logged in");
// 로그인 한 사용자의 경우
//            todo token 값을 통해서 validation user 랑 email 이랑 실제로 같은지 체크할 것
            List<LyricResDto> lyricsResDtos = lyricService.getLyricsForUser(musicId,nickname);
            // music id에 해당하는 Lyrics 를 order 순으로 반환 하면서, Lyric에 맞는 comment 를 좋아요 순으로 반환
            ApiDto<LyricResDto> returnDto = new ApiDto(lyricsResDtos);
            return returnDto;
        }


    }

    @PostMapping("/lyricInput")
    public LyricComment lyricInput(@RequestBody LyricInputReqDto dto){
        log.info("lyricInputReqDto : " + dto);
        LyricComment lyricComment= lyricService.saveLyricComment(dto.lyricId().longValue(), dto.content(), dto.writer());
        return lyricComment;
    }
    @PostMapping("/createNewMusic")
    public ResponseEntity<String> createNewMusic(@RequestBody CreateNewMusicReqDto dto, BindingResult bindingResult){
        log.info(bindingResult.toString());
        log.warn("createNewMusicReqDto : " + dto);
        musicListService.createNewMusic(dto.title(), dto.singer(), dto.songWriter(), dto.postWriter(), dto.lyricWriter(), dto.remixArtist(), dto.released(), dto.videoId(), dto.country(), dto.genre(), dto.tags(),dto.lyric(), dto.lyricComment());
        return ResponseEntity.ok("success");
    }


    @PostMapping("/like")
    public ResponseEntity<String> likeMusic(@RequestBody LikeMusicReqDto dto){
        musicListService.likeMusic( dto.musicId(),dto.email());
        return ResponseEntity.ok("success");
    }

    //todo await axios.post(`http://localhost:8080/update/${musicQuery.id}` => UpdatePost 참고할 것
    //todo const response = await axios.get(`http://localhost:8080/update/${id}`); updateMusicPost 참고할 것


    @DeleteMapping("/music/delete")
    public ResponseEntity<String> deleteMusic(

            @RequestParam(required = false) Optional<String> nickname,
            @RequestParam(required = false) Optional<Long> musicId

    ){
        musicListService.deleteMusic(nickname, musicId);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/update/{musicId}")
    public UpdateMusicResDto getUpdateMusicPost(@PathVariable Long musicId){
        log.info("update music id {}",musicId);
        return musicListService.getUpdateMusic(musicId);
    }
    @PostMapping("/update/{musicId}")
    public ResponseEntity<String> updateMusic(@PathVariable Long musicId, @RequestBody CreateNewMusicReqDto dto, BindingResult bindingResult){
        log.info(bindingResult.toString());
        log.warn("updateMusicReqDto : " + dto);
        musicListService.updateMusic(musicId ,dto.title(), dto.singer(), dto.songWriter(), dto.postWriter(), dto.lyricWriter(), dto.remixArtist(), dto.released(), dto.videoId(), dto.country(), dto.genre(), dto.tags(),dto.lyric(), dto.lyricComment());
        return ResponseEntity.ok("success");
    }

}
