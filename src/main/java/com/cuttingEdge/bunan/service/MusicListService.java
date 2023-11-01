package com.cuttingEdge.bunan.service;

import com.cuttingEdge.bunan.dto.LyricResDto;
import com.cuttingEdge.bunan.dto.MusicListResDto;
import com.cuttingEdge.bunan.entity.Lyric;
import com.cuttingEdge.bunan.entity.LyricComment;
import com.cuttingEdge.bunan.entity.Music;
import com.cuttingEdge.bunan.entity.Tag;
import com.cuttingEdge.bunan.repository.LyricCommentRepository;
import com.cuttingEdge.bunan.repository.LyricRepository;
import com.cuttingEdge.bunan.repository.MusicRepository;
import com.cuttingEdge.bunan.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicListService {
    private final MusicRepository musicRepository;
    private final TagRepository tagRepository;
    private final LyricRepository LyricRepository;
    private final LyricCommentRepository LyricCommentRepository;

    public List<MusicListResDto> getMusics(String country, String genre, String ordering, String search, List<String> tags) {

        List<MusicListResDto> result = musicRepository.findFilteredAndSortedMusic(country, genre, ordering, search, tags).stream().map((m) -> (//m for music
                new MusicListResDto(
                        m.getId(), m.getTitle(), m.getSinger(), m.getSongWriter(),m.getPostWriter(), m.getReleased(), m.getPosted(),
                        m.getModified(), m.getDeleted(), m.getVideoId(), m.getLikes(), m.getViews(), m.getCountry(), m.getGenre(),
                        tagRepository.findAllTagNamesByMusicId(m.getId()).stream()
                                .map(Tag::getName)
                                .collect(Collectors.toList())
                )
        )).collect(Collectors.toList());


        return result;
    }

    public String getVideoId(Long id) {
        if (musicRepository.findById(id).isEmpty()) {
            return null;
        }
        return musicRepository.findById(id).get().getVideoId();
    }


    // music id에 해당하는 Lyrics 를 order 순으로 반환 하면서, Lyric에 맞는 comment 를 좋아요 순으로 반환
    public List<LyricResDto> getLyrics(Long id) {
        if (musicRepository.findById(id).isEmpty()) {
            return null;
        }
        List<LyricResDto> result = LyricRepository.findAllByMusicIdOrderByOrderNumber(id).stream().map((l) -> (
                new LyricResDto(
                        l.getId(), l.getContent(), l.getOrderNumber(), LyricCommentRepository.findAllByLyricIdOrderByLikes(l.getId()).stream().toList()
                )
        )).collect(Collectors.toList());
        log.info("result : " + result);
        return result;
    }

    public void createNewMusic(String title, String singer, String songWriter, String postWriter, Date released, String videoId, String country, String genre, List<String> tags, List<String> lyrics, List<String> lyricComments) {
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
            newLyricComment.setNewLyricComment(newLyric, lyricComments.get(i), postWriter);
            LyricCommentRepository.save(newLyricComment);
        }
    }

}
