package com.cuttingEdge.bunan.service;

import com.cuttingEdge.bunan.constant.Permission;
import com.cuttingEdge.bunan.constant.Role;
import com.cuttingEdge.bunan.dto.LyricResDto;
import com.cuttingEdge.bunan.dto.MusicListResDto;
import com.cuttingEdge.bunan.dto.MusicPostResDto;
import com.cuttingEdge.bunan.dto.UpdateMusicResDto;
import com.cuttingEdge.bunan.entity.*;
import com.cuttingEdge.bunan.exception.AppException;
import com.cuttingEdge.bunan.exception.ErrorCode;
import com.cuttingEdge.bunan.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicListService {
    private final MusicRepository musicRepository;
    private final LyricRepository lyricRepository;
    private final LyricCommentRepository lyricCommentRepository;
    private final MemberRepository memberRepository;
    private final LikeyRepository likeyRepository;


    public MusicPostResDto getMusicPost(Long musicId){
        Music music = musicRepository.findById(musicId).get();
        MusicPostResDto result = new MusicPostResDto(music.getId(),music.getTitle(),music.getSinger(),music.getSongWriter(),music.getPostWriter(),music.getLyricWriter(),music.getRemixArtist(),music.getReleased(),music.getPosted(),music.getModified(), music.getDeleted(),music.getVideoId(),music.getLikes(), music.getViews(),music.getCountry(), music.getGenre(),new ArrayList<>(),false);
        return result;
    }

    public List<MusicListResDto> getMusics(String country, String genre, String ordering, String search, List<String> tags) {

        List<MusicListResDto> result = musicRepository.findFilteredAndSortedMusic(country, genre, ordering, search, tags).stream().map((m) -> {//m for music

            log.info("id : {} lieks : {} songWriter: {}",m.getId(), m.getLikes(),m.getSongWriter());
                 return new MusicListResDto(
                            m.getId(), m.getTitle(), m.getSinger(), m.getSongWriter(), m.getPostWriter(), m.getLyricWriter(),m.getRemixArtist(),m.getReleased(), m.getPosted(),
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
    public List<MusicListResDto> getMusics(String country, String genre, String ordering, String search, List<String> tags, String email){
        log.info("getMusicsForUser : {}",email);
// 현재 사용자가 좋아요한 음악의 ID 목록을 가져옵니다.
        List<Long> likedMusicIds = findLikedMusicIdsByUserEmail(email);
        log.info("Liked music IDs  : {}",likedMusicIds.toString());

        // 필터링 및 정렬된 음악 목록을 가져옵니다.
        List<MusicListResDto> result = musicRepository.findFilteredAndSortedMusic(country, genre, ordering, search, tags).stream().map(m -> {
            boolean isLikedByUser = likedMusicIds.contains(m.getId()); // 현재 음악이 사용자에 의해 좋아요 되었는지 확인합니다.

            log.info("musics id : {} likes : {} liked? : {}", m.getId(), m.getLikes(),isLikedByUser);
            return new MusicListResDto(
                    m.getId(), m.getTitle(), m.getSinger(), m.getSongWriter(), m.getPostWriter(),m.getLyricWriter(),m.getRemixArtist(), m.getReleased(), m.getPosted(),
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

    // music id에 해당하는 Lyrics 를 order 순으로 반환 하면서, Lyric에 맞는 comment 를 좋아요 순으로 반환


    public void createNewMusic(String title, String singer, String songWriter, String postWriter,String lyricWriter,String remixArtist , LocalDate released, String videoId, String country, String genre, List<String> tags, List<String> lyrics, List<String> lyricComments) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate currentDate = now.toLocalDate();

        Member member = memberRepository.findByNickname(postWriter).get();
        Music newMusic = Music.builder()
                .title(title)
                .singer(singer)
                .songWriter(songWriter)
                .postWriter(postWriter)
                .lyricWriter(lyricWriter)
                .remixArtist(remixArtist)
                .released(released)
                .posted(currentDate)
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
            lyricRepository.save(newLyric);
            if(lyricComments != null && lyricComments.size()>i){
                LyricComment newLyricComment = new LyricComment();
                newLyricComment.setNewLyricComment(newLyric, lyricComments.get(i), postWriter, member);
                lyricCommentRepository.save(newLyricComment);
            }
        }
    }

    public void updateMusic(Long musicId,String title, String singer, String songWriter, String postWriter,String lyricWriter,String remixArtist , LocalDate released, String videoId, String country, String genre, List<String> tags, List<String> lyrics) {

        // 현재 사용자의 인증 정보 가져오기
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authenticationToken.getPrincipal();

        // 현재 사용자의 닉네임 가져오기
        String currentNickname = member.getNickname();

        //music, member 선언
        Music music = musicRepository.findById(musicId).orElseThrow(()-> new AppException(ErrorCode.MUSICID_NOT_FOUND,"없는 음악 포스트 입니다."));
//        Member member = memberRepository.findByEmail(currentEmail).orElseThrow(()-> new AppException(ErrorCode.INVALID_EMAIL,"잘못된 유저 입니다."));

        // Music 엔티티의 postWriter와 현재 사용자의 닉네임 비교
        if (!music.getPostWriter().equals(currentNickname)) {
            throw new AppException(ErrorCode.INVALID_USER, "잘못된 사용자 입니다.");
        }
        music.update(title,singer,songWriter,lyricWriter,remixArtist,released,videoId,country,genre);

//        기존의 lyric 을 가져와서 수정하고 lyric comment 는 추가하는 방식으로 하면 기존의 update 도 안사라질듯 ?
        List<Lyric> existedLyrics = lyricRepository.findAllByMusicIdOrderByOrderNumber(musicId);

        log.info(" existed Lyrics : {}",existedLyrics.toString());

//      기존의 lyric 이랑 새로 들어오는 lyric 이랑 길이가 다를 수 있다.
//      새 lyrics 를 기준으로 기존의 lyric을 업데이트 하지만 새 lyric 이 더 길면 새 lyric을 만들면 되고,
//      기존의 lyric이 더 길면 남는 만큼 삭제하자

        if (existedLyrics.size() >= lyrics.size()){

            for (int i=0; i< lyrics.size(); i++){
                Lyric ithLyric = existedLyrics.get(i);
                ithLyric.update(lyrics.get(i));
                lyricRepository.save(ithLyric);

            }
            for (int i =lyrics.size();i<existedLyrics.size();i++){
                lyricRepository.delete(existedLyrics.get(i));
            }

        }else{

            for (int i=0; i<existedLyrics.size(); i++){
                Lyric ithLyric = existedLyrics.get(i);
                ithLyric.update(lyrics.get(i));
                lyricRepository.save(ithLyric);
            }
            for(int i = existedLyrics.size();i< lyrics.size();i++){
                Lyric newLyric = Lyric.builder()
                        .content(lyrics.get(i))
                        .orderNumber(i)
                        .music(music)
                        .build();
                lyricRepository.save(newLyric);

            }

        }

    }


    List<Long> findLikedMusicIdsByUserEmail(String email){

        Long memberId = memberRepository.findByEmail(email).get().getId();
        List<Long> likedMusicIds =  likeyRepository.findAllByMemberId(memberId).stream().map(m->m.getMusic().getId()).collect(Collectors.toList());
        return likedMusicIds;
    }

    public void likeMusic(Long musicId, String email) {
        log.info("musicId : {}, email : {}",musicId,email);
        Long memberID = memberRepository.findByEmail(email).get().getId();
        Music music = musicRepository.findById(musicId).get();
        Optional<Likey> likeyOptional = likeyRepository.findByMemberIdAndMusicId(memberID, musicId);
        likeyOptional.ifPresent(likey -> {
            log.info("likes -1 ");
            // Likey가 존재하는 경우, 삭제
            likeyRepository.delete(likey);
            music.disLikes();
            log.info("music id : {}, music name : {}, music liked: {}, music likes : {}",music.getId(),music.getTitle(), music.getLikes());
            musicRepository.save(music);
        return;

        });
// Likey가 없는 경우, 생성 (또는 다른 작업 수행)
        if (!likeyOptional.isPresent()) {
            log.info("likes +1 ");
            Likey newLikey = Likey.builder().music(music)
                    .created(new Date())
                    .member(memberRepository.findById(memberID).get())
                    .build();
            likeyRepository.save(newLikey);
            music.likes();
            musicRepository.save(music);
//            log.info("music id : {}, music name : {}, music liked: {}, music likes : {}",music.getId(),music.getTitle(), music.getLikes());
//
//            log.info("likes now : {}",musicRepository.findById(musicId).get().getLikes().toString());
            // Likey 생성 또는 다른 작업 수행
            return;
        }
    }

    public void deleteMusic(Optional<Long> musicId){
        // 현재 사용자의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ) {
            throw new AppException(ErrorCode.USERNAME_NOT_FOUND,"올바르지 않은 사용자입니다.");
        }
        Member member = (Member) authentication.getPrincipal();
        log.info("security context member : "+member.getNickname());
        if(member.getAuthorities().contains(Permission.MANAGER_DELETE)) {
            musicRepository.deleteById(musicId.get());
            return;
        }
        if (!musicRepository.existsById(musicId.get())) throw new AppException(ErrorCode.MUSICID_NOT_FOUND,"존재하지 않는 포스트 ID 입니다.");
        if (!member.getNickname().equals(musicRepository.findById(musicId.get()).get().getPostWriter())) throw new AppException(ErrorCode.INVALID_USER,"음악을 포스트한 사용자가 아닙니다.");

        musicRepository.deleteById(musicId.get());

    }


    public UpdateMusicResDto getUpdateMusic(Long musicId) {
        // 현재 사용자의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ) {
            throw new AppException(ErrorCode.USERNAME_NOT_FOUND,"올바르지 않은 사용자입니다.");
        }
        Member member = (Member) authentication.getPrincipal();
        // 현재 사용자의 닉네임 가져오기


//        log.info("current nickname : {} auth : {}", currentEmail,authentication.toString());

        // musicId를 사용하여 Music 엔티티 조회
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new AppException(ErrorCode.MUSICID_NOT_FOUND,"없는 음악 포스트 입니다."));

        // Music 엔티티의 postWriter와 현재 사용자의 닉네임 비교
        if (!music.getPostWriter().equals(member.getNickname())) {
            // postWriter와 현재 사용자가 일치하지 않는 경우에 대한 처리
            throw new AppException(ErrorCode.INVALID_USER,"잘못된 사용자 입니다.");
        }

        List<Lyric> lyrics = lyricRepository.findAllByMusicIdOrderByOrderNumber(musicId);

        String mergedLyrics = lyrics.stream()
                .map(Lyric::getContent)
                .collect(Collectors.joining("\n\n"));

//        log.info("req update LyricWriter : {} RemixArtist : {}  Released : {}", music.getLyricWriter(),music.getRemixArtist(),music.getReleased().toString());
        return new UpdateMusicResDto(music.getTitle(),music.getSinger(),music.getSongWriter(),music.getPostWriter(),music.getLyricWriter(),music.getRemixArtist(),music.getReleased(),music.getVideoId(),music.getCountry(),music.getGenre(),new ArrayList<String>(),mergedLyrics);


    }
}
