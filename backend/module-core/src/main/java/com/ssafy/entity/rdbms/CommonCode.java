package com.ssafy.entity.rdbms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = "parent")
@EqualsAndHashCode(callSuper = true)
public class CommonCode extends BaseEntity{

    private Long id;

    @Column
    private String codeName;

    @JsonIgnore
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private CommonCode parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<CommonCode> children = new ArrayList<>();

}
