package com.cuttingEdge.bunan.service;

import com.cuttingEdge.bunan.dto.LyricResDto;
import com.cuttingEdge.bunan.dto.MusicListResDto;
import com.cuttingEdge.bunan.entity.*;
import com.cuttingEdge.bunan.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicListService {
    private final MusicRepository musicRepository;
    private final LyricRepository LyricRepository;
    private final LyricCommentRepository LyricCommentRepository;
    private final MemberRepository memberRepository;
    private final LikeyRepository likeyRepository;

    public List<MusicListResDto> getMusics(String country, String genre, String ordering, String search, List<String> tags) {

        List<MusicListResDto> result = musicRepository.findFilteredAndSortedMusic(country, genre, ordering, search, tags).stream().map((m) -> {//m for music

            log.info("id : {} lieks : {}",m.getId(), m.getLikes());
                 return new MusicListResDto(
                            m.getId(), m.getTitle(), m.getSinger(), m.getSongWriter(), m.getPostWriter(), m.getReleased(), m.getPosted(),
                            m.getModified(), m.getDeleted(), m.getVideoId(), m.getLikes(), m.getViews(), m.getCountry(), m.getGenre(),
                            new ArrayList<>()
//                        tagRepository.findAllTagNamesByMusicId(m.getId()).stream()
//                                .map(Tag::getName)
//                                .collect(Collectors.toList())
                            , false
                    );

                }

        ).collect(Collectors.toList());


        return result;
    }
    public List<MusicListResDto> getMusicsForUser(String country, String genre, String ordering, String search, List<String> tags, String email){
        log.info("getMusicsForUser : {}",email);
// 현재 사용자가 좋아요한 음악의 ID 목록을 가져옵니다.
        List<Long> likedMusicIds = findLikedMusicIdsByUserEmail(email);
        log.info("Liked music IDs  : {}",likedMusicIds.toString());

        // 필터링 및 정렬된 음악 목록을 가져옵니다.
        List<MusicListResDto> result = musicRepository.findFilteredAndSortedMusic(country, genre, ordering, search, tags).stream().map(m -> {
            boolean isLikedByUser = likedMusicIds.contains(m.getId()); // 현재 음악이 사용자에 의해 좋아요 되었는지 확인합니다.

            log.info("musics id : {} likes : {} liked? : {}", m.getId(), m.getLikes(),isLikedByUser);
            return new MusicListResDto(
                    m.getId(), m.getTitle(), m.getSinger(), m.getSongWriter(), m.getPostWriter(), m.getReleased(), m.getPosted(),
                    m.getModified(), m.getDeleted(), m.getVideoId(), m.getLikes(), m.getViews(), m.getCountry(), m.getGenre(),
                    new ArrayList<>()

//                        tagRepository.findAllTagNamesByMusicId(m.getId()).stream()
//                                .map(Tag::getName)
//                                .collect(Collectors.toList())
                    ,
                    isLikedByUser // 좋아요 여부를 MusicListResDto에 설정합니다.
            );
        }).collect(Collectors.toList());

        return result;
    }

    public String getVideoId(Long id) {
        if (musicRepository.findById(id).isEmpty()) {
            return null;
        }
        return musicRepository.findById(id).get().getVideoId();
    }


    // music id에 해당하는 Lyrics 를 order 순으로 반환 하면서, Lyric에 맞는 comment 를 좋아요 순으로 반환


    public void createNewMusic(String title, String singer, String songWriter, String postWriter, Date released, String videoId, String country, String genre, List<String> tags, List<String> lyrics, List<String> lyricComments) {
        Member member = memberRepository.findByNickname(postWriter).get();
        Music newMusic = Music.builder()
                .title(title)
                .singer(singer)
                .songWriter(songWriter)
                .postWriter(postWriter)
                .released(released)
                .posted(new Date())
                .videoId(videoId)
                .country(country)
                .genre(genre)
                .member(member)
                .likes(0L)
                .build();
        musicRepository.save(newMusic);
        for (int i=0; i<lyrics.size(); i++) {
            Lyric newLyric = Lyric.builder()
                    .content(lyrics.get(i))
                    .orderNumber(i)
                    .music(newMusic)
                    .build();
            LyricRepository.save(newLyric);
            LyricComment newLyricComment = new LyricComment();
            newLyricComment.setNewLyricComment(newLyric, lyricComments.get(i), postWriter, member);
            LyricCommentRepository.save(newLyricComment);
        }
    }


    List<Long> findLikedMusicIdsByUserEmail(String email){

        Long memberId = memberRepository.findByEmail(email).get().getId();
        List<Long> likedMusicIds =  likeyRepository.findAllByMemberId(memberId).get().stream().map(m->m.getMusic().getId()).collect(Collectors.toList());
        return likedMusicIds;
    }

    public void likeMusic(Long musicId, String email) {
        log.info("musicId : {}, email : {}",musicId,email);
        Long memberID = memberRepository.findByEmail(email).get().getId();
        Music music = musicRepository.findById(musicId).get();
        Optional<Likey> likeyOptional = likeyRepository.findByMemberIdAndMusicId(memberID, musicId);
        likeyOptional.ifPresent(likey -> {
            log.warn("likes -1 ");
            // Likey가 존재하는 경우, 삭제
            likeyRepository.delete(likey);
            music.disLikes();
            log.info("music id : {}, music name : {}, music liked: {}, music likes : {}",music.getId(),music.getTitle(), music.getLikes());
            musicRepository.save(music);
        return;

        });
// Likey가 없는 경우, 생성 (또는 다른 작업 수행)
        if (!likeyOptional.isPresent()) {
            log.warn("likes +1 ");
            Likey newLikey = Likey.builder().music(music)
                    .member(memberRepository.findById(memberID).get())
                    .build();
            likeyRepository.save(newLikey);
            music.likes();
            musicRepository.save(music);
            log.info("music id : {}, music name : {}, music liked: {}, music likes : {}",music.getId(),music.getTitle(), music.getLikes());

            log.warn("likes now : {}",musicRepository.findById(musicId).get().getLikes().toString());
            // Likey 생성 또는 다른 작업 수행
            return;
        }
    }
}
