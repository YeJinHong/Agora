package com.ssafy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 887789455L;

    public static final QUser user = new QUser("user");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> classNum = createNumber("classNum", Integer.class);

    public final ListPath<DebateHistory, QDebateHistory> debateHistories = this.<DebateHistory, QDebateHistory>createList("debateHistories", DebateHistory.class, QDebateHistory.class, PathInits.DIRECT2);

    public final ListPath<Debate, QDebate> debates = this.<Debate, QDebate>createList("debates", Debate.class, QDebate.class, PathInits.DIRECT2);

    public final StringPath department = createString("department");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath position = createString("position");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final ListPath<UserDebate, QUserDebate> userDebates = this.<UserDebate, QUserDebate>createList("userDebates", UserDebate.class, QUserDebate.class, PathInits.DIRECT2);

    public final StringPath userEmail = createString("userEmail");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

