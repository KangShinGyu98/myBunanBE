package com.cuttingEdge.bunan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMusic is a Querydsl query type for Music
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMusic extends EntityPathBase<Music> {

    private static final long serialVersionUID = -4877298L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMusic music = new QMusic("music");

    public final StringPath country = createString("country");

    public final DateTimePath<java.util.Date> deleted = createDateTime("deleted", java.util.Date.class);

    public final StringPath genre = createString("genre");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final DateTimePath<java.util.Date> modified = createDateTime("modified", java.util.Date.class);

    public final DateTimePath<java.util.Date> posted = createDateTime("posted", java.util.Date.class);

    public final StringPath postWriter = createString("postWriter");

    public final DateTimePath<java.util.Date> released = createDateTime("released", java.util.Date.class);

    public final StringPath singer = createString("singer");

    public final StringPath songWriter = createString("songWriter");

    public final StringPath title = createString("title");

    public final QUser user;

    public final StringPath videoId = createString("videoId");

    public final NumberPath<Long> views = createNumber("views", Long.class);

    public QMusic(String variable) {
        this(Music.class, forVariable(variable), INITS);
    }

    public QMusic(Path<? extends Music> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMusic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMusic(PathMetadata metadata, PathInits inits) {
        this(Music.class, metadata, inits);
    }

    public QMusic(Class<? extends Music> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

