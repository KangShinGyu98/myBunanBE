package com.cuttingEdge.bunan.repository;

import com.cuttingEdge.bunan.entity.Music;
import com.cuttingEdge.bunan.entity.QMusic;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Slf4j
@Transactional
@Repository
@RequiredArgsConstructor
public class MusicQuerydslRepositoryImpl implements MusicQuerydslRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;



    @Override
    public List<Music> findFilteredAndSortedMusic(String country, String genre, String ordering, String search, List<String> tags) {
        log.info("country : " + country);
        log.info("genre : " + genre);
        log.info("ordering : " + ordering);
        log.info("search : " + search);

        QMusic music = QMusic.music;

        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(null, null);
        if (ordering != null ){
            switch (ordering) {
                case "posted":
                    orderSpecifier = music.posted.desc();
                    break;
                case "released":
                    orderSpecifier = music.released.desc();
                    break;
                case "views":
                    orderSpecifier = music.views.desc();
                    break;
                case "likes":
                    orderSpecifier = music.likes.desc();
                    break;
            }
        }

        JPAQuery <Music> query = queryFactory.selectFrom(music);

        if (country != null) {
            query.where(music.country.eq(country));
        }
        if (genre != null) {
            query.where(music.genre.eq(genre));
        }
        if (search != null) {
            query.where(music.title.contains(search));
        }
        if (orderSpecifier.getOrder() != null){
            query.orderBy(orderSpecifier);
        }
        List<Music> result = query.fetch();

        return result;
    }
}
