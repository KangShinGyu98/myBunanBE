package com.cuttingEdge.bunan.repository;

import com.cuttingEdge.bunan.entity.Music;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicQuerydslRepository {
    List<Music> findFilteredAndSortedMusic(String country, String genre, String ordering, String search, List<String> tags);
}
