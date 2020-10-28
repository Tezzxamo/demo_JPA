package com.example.demo.dao.dbo;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * @author y
 */

@Entity
@Table(name = "tb_student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private int id;

    @Column
    private String gender;

    @Column
    private String name;

    @Column
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "c_id")
    private Clazz clazz;

    @Override
    public String toString() {
        return "Student:[s_id="+getId()+
                " ,s_name="+getName()+
                " ,s_gender="+getGender()+
                " ,s_birthday="+getBirthday()+
                " ,class_id="+getClazz().getId()+
                "]";
    }
}
