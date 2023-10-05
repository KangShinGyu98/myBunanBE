package com.CuttingEdge.bunan.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMusic is a Querydsl query type for Music
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMusic extends EntityPathBase<Music> {

    private static final long serialVersionUID = -202673074L;

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

    public final StringPath videoId = createString("videoId");

    public final NumberPath<Long> views = createNumber("views", Long.class);

    public QMusic(String variable) {
        super(Music.class, forVariable(variable));
    }

    public QMusic(Path<? extends Music> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMusic(PathMetadata metadata) {
        super(Music.class, metadata);
    }

}

