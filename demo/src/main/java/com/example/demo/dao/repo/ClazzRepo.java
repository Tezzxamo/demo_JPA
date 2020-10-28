package com.example.demo.dao.repo;

import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * @author Tethamo_zzx
 * @date 2020-10-26  上午 09:59
 */
public interface ClazzRepo extends JpaRepository<Clazz,Integer> {

    /**
     * 根据年级找到所有的班级
     * @param g_id 年级id
     * @return 所有的班级
     */
    Set<Clazz> findByGradeId(Integer g_id);



//    Clazz findByGradeId(Integer g_id);
}
