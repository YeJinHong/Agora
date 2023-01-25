package com.ssafy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = 887333184L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFile file = new QFile("file");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath extension = createString("extension");

    public final QFileManager fileManager;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath originFileName = createString("originFileName");

    public final StringPath savedFileName = createString("savedFileName");

    public final StringPath savedPath = createString("savedPath");

    public final NumberPath<Float> size = createNumber("size", Float.class);

    public QFile(String variable) {
        this(File.class, forVariable(variable), INITS);
    }

    public QFile(Path<? extends File> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFile(PathMetadata metadata, PathInits inits) {
        this(File.class, metadata, inits);
    }

    public QFile(Class<? extends File> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fileManager = inits.isInitialized("fileManager") ? new QFileManager(forProperty("fileManager")) : null;
    }

}

