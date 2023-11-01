package com.cuttingEdge.bunan.repository;

import com.cuttingEdge.bunan.entity.Lyric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LyricRepository extends JpaRepository<Lyric,Long>{
    public List<Lyric> findAllByMusicId(Long musicId);
    public List<Lyric> findAllByMusicIdOrderByOrderNumber(Long musicId);
}
