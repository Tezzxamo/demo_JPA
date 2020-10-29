package com.example.demo.dao.dbo;


import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_grade")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guuid")
    @Type(type = "uuid-char")
    private UUID guuid;

    @Column
    private String gname;

    @Column
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "grade",fetch = FetchType.EAGER)
    private Set<Clazz> clazzList;

}
