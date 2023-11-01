package com.cuttingEdge.bunan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLyricComment is a Querydsl query type for LyricComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLyricComment extends EntityPathBase<LyricComment> {

    private static final long serialVersionUID = -1436378377L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLyricComment lyricComment = new QLyricComment("lyricComment");

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> created = createDateTime("created", java.util.Date.class);

    public final DateTimePath<java.util.Date> deleted = createDateTime("deleted", java.util.Date.class);

    public final NumberPath<Integer> dislikes = createNumber("dislikes", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final QLyric lyric;

    public final DateTimePath<java.util.Date> modified = createDateTime("modified", java.util.Date.class);

    public final NumberPath<Integer> reports = createNumber("reports", Integer.class);

    public final QUser user;

    public final StringPath writer = createString("writer");

    public QLyricComment(String variable) {
        this(LyricComment.class, forVariable(variable), INITS);
    }

    public QLyricComment(Path<? extends LyricComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLyricComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLyricComment(PathMetadata metadata, PathInits inits) {
        this(LyricComment.class, metadata, inits);
    }

    public QLyricComment(Class<? extends LyricComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lyric = inits.isInitialized("lyric") ? new QLyric(forProperty("lyric"), inits.get("lyric")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

