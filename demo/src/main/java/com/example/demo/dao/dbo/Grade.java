package com.example.demo.dao.dbo;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_grade")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String gName;

    @Column
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "grade",fetch = FetchType.EAGER)
    private Set<Clazz> clazzList;

}
