package com.ssafy.entity.rdbms;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFaq is a Querydsl query type for Faq
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFaq extends EntityPathBase<Faq> {

    private static final long serialVersionUID = 31414778L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFaq faq = new QFaq("faq");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath category = createString("category");

    public final StringPath comment = createString("comment");

    public final StringPath content = createString("content");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.time.LocalDateTime> registTime = createDateTime("registTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public final QUser user;

    public QFaq(String variable) {
        this(Faq.class, forVariable(variable), INITS);
    }

    public QFaq(Path<? extends Faq> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFaq(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFaq(PathMetadata metadata, PathInits inits) {
        this(Faq.class, metadata, inits);
    }

    public QFaq(Class<? extends Faq> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

