package com.CuttingEdge.bunan.Repository;

import com.CuttingEdge.bunan.Entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music,Long> {
}
