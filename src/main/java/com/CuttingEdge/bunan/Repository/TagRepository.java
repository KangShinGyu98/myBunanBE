package com.CuttingEdge.bunan.Repository;

import com.CuttingEdge.bunan.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    public List<Tag> findAllTagNamesByMusicId(Long musicId);
}
