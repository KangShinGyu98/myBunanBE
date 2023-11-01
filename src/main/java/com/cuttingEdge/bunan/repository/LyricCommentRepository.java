package com.cuttingEdge.bunan.repository;

import com.cuttingEdge.bunan.entity.LyricComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LyricCommentRepository extends JpaRepository<LyricComment,Long>{
    public List<LyricComment> findAllByLyricIdOrderByLikes(Long lyricId);
}
