package com.example.demo.service.impl;

import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Student;
import com.example.demo.dao.repo.ClazzRepo;
import com.example.demo.dao.repo.GradeRepo;
import com.example.demo.dao.repo.StudentRepo;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzx
 */
@Service
public class StudentServiceImpl implements StudentService {

    StudentRepo studentRepo;
    ClazzRepo clazzRepo;
    GradeRepo gradeRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo,
                              ClazzRepo clazzRepo,
                              GradeRepo gradeRepo) {
        this.studentRepo = studentRepo;
        this.clazzRepo = clazzRepo;
        this.gradeRepo = gradeRepo;
    }

    @Override
    public Set<Student> findStudentsByGradeId(Integer g_id) {
        // 没有校验
        // LIU

        Set<Clazz> classSet = clazzRepo.findByGradeId(g_id);

        List<Set<Student>> setList = clazzRepo.findByGradeId(g_id)
                .stream()
                .map(Clazz::getStudentList)
                .collect(Collectors.toList());
        Set<Student> studentSet = new TreeSet<>(new StudentComparator());
        for (Clazz c:classSet){
            studentSet.addAll(studentRepo.findByClazzId(c.getId()));
        }
        return studentSet;
    }

    static class StudentComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getId()-o2.getId();
        }
    }
}
