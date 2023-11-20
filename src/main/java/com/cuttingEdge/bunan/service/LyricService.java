package com.cuttingEdge.bunan.service;

import com.cuttingEdge.bunan.dto.LyricCommentResDto;
import com.cuttingEdge.bunan.dto.LyricResDto;
import com.cuttingEdge.bunan.dto.MusicListResDto;
import com.cuttingEdge.bunan.entity.*;
import com.cuttingEdge.bunan.exception.AppException;
import com.cuttingEdge.bunan.exception.ErrorCode;
import com.cuttingEdge.bunan.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LyricService {
    private final LyricRepository lyricRepository;
    private final LyricCommentRepository lyricCommentRepository;
    private final MemberRepository memberRepository;
    private final MusicRepository musicRepository;
    private final LyricCommentLikeyRepository lyricCommentLikeyRepository;
    public LyricComment saveLyricComment( Long lyricId,String content, String writer) {

        LyricComment lyricComment = new LyricComment();
        Lyric lyric = lyricRepository.findById(lyricId).orElseThrow(()->new IllegalArgumentException("해당 가사가 없습니다."));
        lyricComment.setNewLyricComment( lyric, content, writer, memberRepository.findByNickname(writer).get());
        lyricCommentRepository.save(lyricComment);
        return lyricComment;
    }
    public List<LyricResDto> getLyrics(Long musicId) { //music id
        //존재하는 음악인지 musicID 체크
        if (musicRepository.findById(musicId).isEmpty()) {
            throw new AppException(ErrorCode.MUSICID_NOT_FOUND,"존재하지 않는 음악입니다.");
        }

        List<LyricResDto> result = lyricRepository.findAllByMusicIdOrderByOrderNumber(musicId).stream().map((l) -> {
                List<LyricCommentResDto> comments = lyricCommentRepository.findAllByLyricIdOrderByLikesDesc(l.getId()).stream().map( c->(
                        new LyricCommentResDto(c.getId(),c.getContent(),c.getLikes(),false,c.getDislikes(),c.getWriter(),c.getCreated())
                        )).collect(Collectors.toList());
                return new LyricResDto(l.getId(), l.getContent(), l.getOrderNumber(), comments);
        }).collect(Collectors.toList());
//        log.info("result : " + result);
        return result;
    }
    public List<LyricResDto> getLyricsForUser(Long musicId,String nickname) { //music id
        //존재하는 음악인지 musicID 체크
        if (musicRepository.findById(musicId).isEmpty()) {
            throw new AppException(ErrorCode.MUSICID_NOT_FOUND,"존재하지 않는 음악입니다.");
        }

        //Likey 값 확인하기
        List<Long> likedLyricCommentIds = findLikedLyricCommentIdsByUserNickname(nickname);
        log.info("likedLyricCommentIds {}" ,likedLyricCommentIds.stream().toList());
        //결과값 만들기,
        List<LyricResDto> result = lyricRepository.findAllByMusicIdOrderByOrderNumber(musicId).stream().map((l) -> {
            List<LyricCommentResDto> comments = lyricCommentRepository.findAllByLyricIdOrderByLikesDesc(l.getId()).stream().map( c->(
                    new LyricCommentResDto(c.getId(),c.getContent(),c.getLikes(),likedLyricCommentIds.contains(c.getId()),c.getDislikes(),c.getWriter(),c.getCreated())
            )).collect(Collectors.toList());
            return new LyricResDto(l.getId(), l.getContent(), l.getOrderNumber(), comments);
        }).collect(Collectors.toList());
//        log.info("result : " + result);
        return result;
    }
    List<Long> findLikedLyricCommentIdsByUserNickname(String nickname){

        Long memberId = memberRepository.findByNickname(nickname).get().getId();
        List<Long> likedLyricCommentIds =  lyricCommentLikeyRepository.findAllByMemberId(memberId).get().stream().map(cl -> cl.getLyricComment().getId() ).collect(Collectors.toList());
        return likedLyricCommentIds;
    }

    public void likeLyricComment(Long lyricCommentId, String email){
        log.info("lyricComment Id : {}, email : {}",lyricCommentId,email);
        Long memberID = memberRepository.findByEmail(email).get().getId();
        LyricComment lyricComment = lyricCommentRepository.findById(lyricCommentId).get();
        Optional<LyricCommentLikey> likeyOptional = lyricCommentLikeyRepository.findByLyricCommentIdAndMemberId(lyricCommentId,memberID);
        likeyOptional.ifPresent(likey -> {
            log.warn("likes -1 ");
            // Likey가 존재하는 경우, 삭제
            lyricCommentLikeyRepository.delete(likey);
            lyricComment.undoLike();
            log.info("lyricComent id : {}, lyricComment content : {}, lyricComment liked: {}, lyricComment likes : {}",lyricComment.getId(),lyricComment.getContent(), lyricComment.getLikes());
            lyricCommentRepository.save(lyricComment);
            return;
        });
// Likey가 없는 경우, 생성 (또는 다른 작업 수행)

        if (!likeyOptional.isPresent()) {
            log.warn("likes +1 ");
            LyricCommentLikey newLikey = LyricCommentLikey.builder().lyricComment(lyricComment)
                    .member(memberRepository.findById(memberID).get())
                    .build();
            lyricCommentLikeyRepository.save(newLikey);
            lyricComment.likes();
            lyricCommentRepository.save(lyricComment);
            log.info("lyricComent id : {}, lyricComment content : {}, lyricComment liked: {}, lyricComment likes : {}",lyricComment.getId(),lyricComment.getContent(), lyricComment.getLikes());

            log.warn("likes now : {}",lyricCommentRepository.findById(lyricCommentId).get().getLikes().toString());
            // Likey 생성 또는 다른 작업 수행
        }
    }


}
