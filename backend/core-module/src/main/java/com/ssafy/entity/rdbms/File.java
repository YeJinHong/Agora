package com.ssafy.entity.rdbms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class File extends BaseEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_manager_id")
    private FileManager fileManager;

    private String originFileName;

    @Column
    private String savedFileName;

    @Column
    private String savedPath;

    @Column
    private Float size;

    @Column
    private String extension;
}
