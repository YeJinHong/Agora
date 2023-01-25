package com.ssafy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDebaseResult is a Querydsl query type for DebaseResult
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDebaseResult extends EntityPathBase<DebaseResult> {

    private static final long serialVersionUID = -1734909613L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDebaseResult debaseResult = new QDebaseResult("debaseResult");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QDebate debate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mvp = createString("mvp");

    public final StringPath summary = createString("summary");

    public QDebaseResult(String variable) {
        this(DebaseResult.class, forVariable(variable), INITS);
    }

    public QDebaseResult(Path<? extends DebaseResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDebaseResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDebaseResult(PathMetadata metadata, PathInits inits) {
        this(DebaseResult.class, metadata, inits);
    }

    public QDebaseResult(Class<? extends DebaseResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.debate = inits.isInitialized("debate") ? new QDebate(forProperty("debate"), inits.get("debate")) : null;
    }

}

