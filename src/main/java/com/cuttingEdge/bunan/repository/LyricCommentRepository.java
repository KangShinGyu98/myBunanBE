package com.cuttingEdge.bunan.repository;

import com.cuttingEdge.bunan.entity.LyricComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface LyricCommentRepository extends JpaRepository<LyricComment,Long>{
    public Optional<List<LyricComment>> findAllByLyricIdOrderByLikes(Long lyricId);
    public Optional<List<LyricComment>> findAllByMemberId(Long memberId);
    public Optional<LyricComment> findById(Long lyricCommentId);

}
