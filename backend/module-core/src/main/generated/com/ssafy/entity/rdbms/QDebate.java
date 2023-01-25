package com.ssafy.entity.rdbms;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDebate is a Querydsl query type for Debate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDebate extends EntityPathBase<Debate> {

    private static final long serialVersionUID = -479133299L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDebate debate = new QDebate("debate");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> callEndTime = createDateTime("callEndTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> callStartTime = createDateTime("callStartTime", java.time.LocalDateTime.class);

    public final StringPath category = createString("category");

    public final QDebaseResult debaseResult;

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.time.LocalDateTime> insertedTime = createDateTime("insertedTime", java.time.LocalDateTime.class);

    public final StringPath mode = createString("mode");

    public final StringPath moderatorOnOff = createString("moderatorOnOff");

    public final QUser owner;

    public final StringPath status = createString("status");

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    public final StringPath title = createString("title");

    public QDebate(String variable) {
        this(Debate.class, forVariable(variable), INITS);
    }

    public QDebate(Path<? extends Debate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDebate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDebate(PathMetadata metadata, PathInits inits) {
        this(Debate.class, metadata, inits);
    }

    public QDebate(Class<? extends Debate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.debaseResult = inits.isInitialized("debaseResult") ? new QDebaseResult(forProperty("debaseResult"), inits.get("debaseResult")) : null;
        this.owner = inits.isInitialized("owner") ? new QUser(forProperty("owner")) : null;
    }

}

