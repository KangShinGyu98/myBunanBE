package com.CuttingEdge.bunan.Repository;

import com.CuttingEdge.bunan.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    public List<Tag> findAllTagNamesByMusicId(Long musicId);
}
