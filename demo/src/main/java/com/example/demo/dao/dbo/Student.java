package com.example.demo.dao.dbo;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


/**
 * @author y
 */

@Entity
@Table(name = "tb_student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "suuid")
    @Type(type = "uuid-char")
    private UUID suuid;

    @Column(columnDefinition = "int(10) not null UNIQUE auto_increment")
    private Integer snumber;

    @Column
    private String sgender;

    @Column
    private String sname;

    @Column
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "cname", referencedColumnName = "cname")
    private Clazz clazz;

    @Override
    public String toString() {
        return "Student:[uuid = " + getSuuid() +
                " ,s_number = " + getSnumber() +
                " ,s_name = " + getSname() +
                " ,s_gender =" + getSgender() +
                " ,s_birthday = " + getBirthday() +
                " ,class_name = " + getClazz().getCname() +
                "]";
    }
}
