package com.example.demo;


import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Grade;
import com.example.demo.dao.dbo.Student;
import com.example.demo.dao.repo.ClazzRepo;
import com.example.demo.dao.repo.GradeRepo;
import com.example.demo.dao.repo.StudentRepo;
import com.example.demo.service.ClazzService;
import com.example.demo.service.GradeService;
import com.example.demo.service.StudentService;
import com.example.demo.utils.ChineseNameUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.*;


@SpringBootTest
public class Test1 {


    StudentRepo studentRepo;
    GradeRepo gradeRepo;
    ClazzRepo clazzRepo;
    StudentService studentService;
    ClazzService clazzService;
    GradeService gradeService;

    @Autowired
    public Test1(StudentRepo studentRepo,
                 GradeRepo gradeRepo,
                 ClazzRepo clazzRepo,
                 StudentService studentService,
                 GradeService gradeService,
                 ClazzService clazzService) {
        this.studentRepo = studentRepo;
        this.gradeRepo = gradeRepo;
        this.clazzRepo = clazzRepo;
        this.studentService = studentService;
        this.gradeService = gradeService;
        this.clazzService = clazzService;
    }

    /**
     * 增加随机的学生（没有加入班级）
     */
    @Test
    public void test1() {
        Set<Student> list = new HashSet<>();
        Clazz clazz = new Clazz();
        Grade grade = new Grade();

        //换成输入的参数
        int g_id = 5;

        if (g_id >= 1 && g_id <= 6) {
            grade.setId(g_id);
        }
        for (int i = 1; i <= 30; i++) {
            Student student = new Student();
            boolean simple = true;
            Random random = new Random();
            random.nextInt(5);
            if (random.nextInt(5) <= 3) {
                student.setGender("male");
                simple = true;
                student.setName(ChineseNameUtil.getName(simple, i % 2 + 2));
            } else {
                student.setGender("female");
                simple = false;
                student.setName(ChineseNameUtil.getName(simple, i % 2 + 3));
            }
            student.setClazz(clazz);
            student.setBirthday(LocalDate.now());
            list.add(student);
            System.out.println(student.toString());
        }
        clazz.setStudentList(list);
        clazz.setGrade(grade);
        clazzRepo.save(clazz);
        studentRepo.saveAll(list);
    }


    /**
     * 根据c_id获取班级里的所有学生
     */
    @Test
    void allStudentsFromClazz() {
        Set<Student> studentSet = studentRepo.findStudentsByClazzId(1);
        for (Student s : studentSet) {
            System.out.println(s);
        }
    }

    /**
     * 根据g_id获取年级里的所有学生(使用studentService中被实现的方法)
     */
    @Test
    void allStudentsFromGrade() {
        Set<Student> studentSet = studentService.findStudentsByGradeId(2);
        for (Student s : studentSet) {
            System.out.println(s);
        }
    }


    /**
     * 增加年级（使用Jpa的save()）
     */
    @Test
    void addGrade() {
        Grade grade = new Grade();
        grade.setGName("1年级");
        gradeRepo.save(grade);
        System.out.println(grade);
    }


    @Test
    void testete() {
        Map<String, List<Clazz>> map =clazzService.classifyByClazz();
        System.out.println(map);
    }
}
