package com.cuttingEdge.bunan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLyric is a Querydsl query type for Lyric
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLyric extends EntityPathBase<Lyric> {

    private static final long serialVersionUID = -5682616L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLyric lyric = new QLyric("lyric");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMusic music;

    public final NumberPath<Integer> orderNumber = createNumber("orderNumber", Integer.class);

    public final QUser user;

    public QLyric(String variable) {
        this(Lyric.class, forVariable(variable), INITS);
    }

    public QLyric(Path<? extends Lyric> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLyric(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLyric(PathMetadata metadata, PathInits inits) {
        this(Lyric.class, metadata, inits);
    }

    public QLyric(Class<? extends Lyric> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.music = inits.isInitialized("music") ? new QMusic(forProperty("music"), inits.get("music")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

