package com.CuttingEdge.bunan.Repository;

import com.CuttingEdge.bunan.Entity.Music;
import com.CuttingEdge.bunan.Entity.QMusic;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeList;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
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
