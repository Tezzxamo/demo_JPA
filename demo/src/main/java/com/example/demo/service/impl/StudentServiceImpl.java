package com.example.demo.service.impl;

import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Student;
import com.example.demo.dao.repo.ClazzRepo;
import com.example.demo.dao.repo.GradeRepo;
import com.example.demo.dao.repo.StudentRepo;
import com.example.demo.service.GradeService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zzx
 */
@Service
public class StudentServiceImpl implements StudentService {

    StudentRepo studentRepo;
    ClazzRepo clazzRepo;
    GradeRepo gradeRepo;
    GradeService gradeService;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo,
                              ClazzRepo clazzRepo,
                              GradeRepo gradeRepo,
                              GradeService gradeService) {
        this.studentRepo = studentRepo;
        this.clazzRepo = clazzRepo;
        this.gradeRepo = gradeRepo;
        this.gradeService = gradeService;
    }

    @Override
    public Set<Student> findByGradeGname(String gname) throws Exception {
        // 校验
        if (!gradeService.existByName(gname)) {
            throw new Exception("没有该年级");
        }
        Set<Student> studentSet = new HashSet<>();
        //流的方式获取studentSet然后返回
        clazzRepo.findByGradeGname(gname)
                .stream()
                .map(Clazz::getStudentList)
                .collect(Collectors.toSet())
                .forEach(studentSet::addAll);
        return studentSet;
    }
}
