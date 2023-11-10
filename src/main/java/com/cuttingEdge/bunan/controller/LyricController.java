package com.cuttingEdge.bunan.controller;


import com.cuttingEdge.bunan.dto.LikeLyricCommentReqDto;
import com.cuttingEdge.bunan.dto.LikeMusicReqDto;
import com.cuttingEdge.bunan.dto.MailEmailReqDto;
import com.cuttingEdge.bunan.entity.LyricCommentLikey;
import com.cuttingEdge.bunan.service.LyricService;
import com.cuttingEdge.bunan.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LyricController {

    private final LyricService lyricService;

    @PostMapping("/lyricComment/like")
    public ResponseEntity<String> likeMusic(@RequestBody LikeLyricCommentReqDto dto){
        lyricService.likeLyricComment( dto.lyricCommentId(),dto.email());
        return ResponseEntity.ok("success");
    }


}
//출처 : https://velog.io/@kjh950330/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%EC%9D%B4%EB%A9%94%EC%9D%BC-%EC%9D%B8%EC%A6%9D-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84