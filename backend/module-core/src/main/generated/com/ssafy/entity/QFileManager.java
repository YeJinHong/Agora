package com.ssafy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileManager is a Querydsl query type for FileManager
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFileManager extends EntityPathBase<FileManager> {

    private static final long serialVersionUID = 1078074957L;

    public static final QFileManager fileManager = new QFileManager("fileManager");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final EnumPath<FileOrigin> fileOrigin = createEnum("fileOrigin", FileOrigin.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QFileManager(String variable) {
        super(FileManager.class, forVariable(variable));
    }

    public QFileManager(Path<? extends FileManager> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileManager(PathMetadata metadata) {
        super(FileManager.class, metadata);
    }

}

