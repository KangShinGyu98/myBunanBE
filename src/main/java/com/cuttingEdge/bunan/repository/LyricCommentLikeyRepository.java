package com.cuttingEdge.bunan.repository;

import com.cuttingEdge.bunan.entity.Likey;
import com.cuttingEdge.bunan.entity.LyricCommentLikey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LyricCommentLikeyRepository extends JpaRepository<LyricCommentLikey,Long>{
    Optional<List<LyricCommentLikey>> findAllByMemberId(Long memberId);
    Optional<LyricCommentLikey> findByLyricCommentIdAndMemberId(Long lyricCommentId,Long memberId);

}
