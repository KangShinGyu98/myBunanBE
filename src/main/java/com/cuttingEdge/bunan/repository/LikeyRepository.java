package com.cuttingEdge.bunan.repository;

import com.cuttingEdge.bunan.entity.Likey;
import com.cuttingEdge.bunan.entity.Lyric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface LikeyRepository extends JpaRepository<Likey,Long>{
    List<Likey> findAllByMemberId(Long memberId);
    Optional<Likey> findByMemberIdAndMusicId(Long memberId, Long musicId);
}
