package com.cuttingEdge.bunan.repository;

import com.cuttingEdge.bunan.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music,Long>, MusicQuerydslRepository{
}
