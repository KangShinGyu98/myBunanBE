package com.CuttingEdge.bunan.Repository;

import com.CuttingEdge.bunan.Entity.Music;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicQuerydslRepository {
    List<Music> findFilteredAndSortedMusic(String country, String genre, String ordering, String search, List<String> tags);
}
