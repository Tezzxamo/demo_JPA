package com.example.demo.dao.dbo;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "tb_class")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Clazz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cuuid")
    @Type(type = "uuid-char")
    private UUID cuuid;

    @Column
    private String cname;

    @ManyToOne
    @JoinColumn(name = "gName", referencedColumnName = "gName")
    private Grade grade;

    @Column
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clazz", fetch = FetchType.EAGER)
    private Set<Student> studentList;


    @Override
    public String toString() {
        return "Clazz:[uuid = " + getCuuid() +
                " ,c_name = " + getCname() +
                " ,Grade_name = " + getGrade().getGname() +
                "]";
    }
}
