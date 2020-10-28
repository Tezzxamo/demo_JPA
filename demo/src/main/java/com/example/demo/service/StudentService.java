package com.example.demo.service;


import com.example.demo.dao.dbo.Student;

import java.util.Set;

/**
 * @author zzx
 */
public interface StudentService {

    /**
     * 通过g_id来获取年级中的所有学生
     * @param g_id 年级id
     * @return 年级中的所有学生
     */
    Set<Student> findStudentsByGradeId(Integer g_id);
}
