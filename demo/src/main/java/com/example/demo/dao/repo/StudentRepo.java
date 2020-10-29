package com.example.demo.dao.repo;

import com.example.demo.dao.dbo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author zzx
 */

public interface StudentRepo extends JpaRepository<Student, UUID> {

    /**
     * 根据cid获取班级里的所有学生
     * @param cname 班级id
     * @return 返回班级里的所有学生
     */
    Set<Student> findByClazzCname(String cname);


//    Set<Student> findByClazzIn(List<Integer> ids);

}
