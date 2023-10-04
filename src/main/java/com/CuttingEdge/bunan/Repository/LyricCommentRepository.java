package com.CuttingEdge.bunan.Repository;

import com.CuttingEdge.bunan.Entity.Lyric;
import com.CuttingEdge.bunan.Entity.LyricComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LyricCommentRepository extends JpaRepository<LyricComment,Long>{
    public List<LyricComment> findAllByLyricIdOrderByLikes(Long lyricId);
}
