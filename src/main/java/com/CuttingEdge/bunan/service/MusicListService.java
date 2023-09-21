package com.CuttingEdge.bunan.service;

import com.CuttingEdge.bunan.Dto.MusicListDto;
import com.CuttingEdge.bunan.Entity.Tag;
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

    public List<MusicListDto> getMusics(Integer countryId, Integer genreId, String ordering, String search, List<String> tags) {
        log.warn("parameters : ");
        log.warn(countryId.toString());
        log.warn(genreId.toString());
        log.warn(ordering);
        log.warn(search);
        log.warn(tags.toString());

        List<MusicListDto> result = musicRepository.findAll().stream().map((m)->(//m for music
                new MusicListDto(
                        m.getId(),m.getName(),m.getSinger(),m.getWriter(),m.getReleased(),m.getPosted(),
                        m.getModified(),m.getDeleted(),m.getVideoId(),m.getLikes(),m.getViews(),m.getCountry(),m.getGenre(),
                        tagRepository.findAllTagNamesByMusicId(m.getId()).stream()
                                .map(Tag::getName)
                                .collect(Collectors.toList())
                )
        )).collect(Collectors.toList());;

        return result;
    }
}
