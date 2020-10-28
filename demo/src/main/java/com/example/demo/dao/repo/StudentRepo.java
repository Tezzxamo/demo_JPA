package com.example.demo.dao.repo;

import com.example.demo.dao.dbo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

/**
 * @author zzx
 */

public interface StudentRepo extends JpaRepository<Student,Integer> {

    /**
     * 根据c_id获取班级里的所有学生
     * @param c_id 班级id
     * @return 返回班级里的所有学生
     */
    Set<Student> findByClazzId(Integer c_id);


//    Set<Student> findByClazzIn(List<Integer> ids);

}
