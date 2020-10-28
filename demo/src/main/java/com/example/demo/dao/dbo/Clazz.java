package com.example.demo.dao.dbo;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tb_class")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "g_id")
    private Grade grade;

    @Column
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "clazz",fetch = FetchType.EAGER)
    private Set<Student> studentList;

}
