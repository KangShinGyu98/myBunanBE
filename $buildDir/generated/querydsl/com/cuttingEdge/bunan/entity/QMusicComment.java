package com.cuttingEdge.bunan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMusicComment is a Querydsl query type for MusicComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMusicComment extends EntityPathBase<MusicComment> {

    private static final long serialVersionUID = 1389028977L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMusicComment musicComment = new QMusicComment("musicComment");

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> created = createDateTime("created", java.util.Date.class);

    public final DateTimePath<java.util.Date> deleted = createDateTime("deleted", java.util.Date.class);

    public final NumberPath<Integer> dislikes = createNumber("dislikes", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final DateTimePath<java.util.Date> modified = createDateTime("modified", java.util.Date.class);

    public final QMusic music;

    public final NumberPath<Integer> reports = createNumber("reports", Integer.class);

    public final QUser user;

    public final NumberPath<Integer> writer = createNumber("writer", Integer.class);

    public QMusicComment(String variable) {
        this(MusicComment.class, forVariable(variable), INITS);
    }

    public QMusicComment(Path<? extends MusicComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMusicComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMusicComment(PathMetadata metadata, PathInits inits) {
        this(MusicComment.class, metadata, inits);
    }

    public QMusicComment(Class<? extends MusicComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.music = inits.isInitialized("music") ? new QMusic(forProperty("music"), inits.get("music")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

