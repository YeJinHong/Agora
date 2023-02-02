package com.ssafy.entity.rdbms;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CommonCode extends BaseEntity{

    @Column
    private String codeName;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CommonCode parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<CommonCode> children = new ArrayList<>();

}
