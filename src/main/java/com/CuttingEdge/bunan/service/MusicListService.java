package com.CuttingEdge.bunan.service;

import com.CuttingEdge.bunan.Dto.LirycsResDto;
import com.CuttingEdge.bunan.Dto.MusicListResDto;
import com.CuttingEdge.bunan.Entity.LyricComment;
import com.CuttingEdge.bunan.Entity.Tag;
import com.CuttingEdge.bunan.Repository.LyricCommentRepository;
import com.CuttingEdge.bunan.Repository.LyricRepository;
import com.CuttingEdge.bunan.Repository.MusicRepository;
import com.CuttingEdge.bunan.Repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
                        m.getId(), m.getTitle(), m.getSinger(), m.getWriter(), m.getReleased(), m.getPosted(),
                        m.getModified(), m.getDeleted(), m.getVideoId(), m.getLikes(), m.getViews(), m.getCountry(), m.getGenre(),
                        tagRepository.findAllTagNamesByMusicId(m.getId()).stream()
                                .map(Tag::getName)
                                .collect(Collectors.toList())
                )
        )).collect(Collectors.toList());
        ;

        return result;
    }

    public String getVideoId(Long id) {
        if (musicRepository.findById(id).isEmpty()) {
            return null;
        }
        return musicRepository.findById(id).get().getVideoId();
    }


    // music id에 해당하는 Lyrics 를 order 순으로 반환 하면서, Lyric에 맞는 comment 를 좋아요 순으로 반환
    public List<LirycsResDto> getLyrics(Long id) {
        if (musicRepository.findById(id).isEmpty()) {
            return null;
        }
        List<LirycsResDto> result = LyricRepository.findAllByMusicIdOrderByOrderNumber(id).stream().map((l) -> (
                new LirycsResDto(
                        l.getId(), l.getContent(), l.getOrderNumber(), LyricCommentRepository.findAllByLyricIdOrderByLikes(l.getId())
                )
        )).collect(Collectors.toList());
        return result;
    }
}