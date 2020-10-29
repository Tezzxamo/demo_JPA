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
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
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
        Clazz clazz = clazzRepo.findByCname("2班").orElseThrow(
                () -> new ExceptionIncludingMockitoWarnings("班级有误或不存在", new Exception())
        );
//        Grade grade = new Grade();
//        int g_id = 1;
//        grade = gradeRepo.findByGid(g_id).get();
        for (int i = 1; i <= 30; i++) {
            Student student = new Student();
            if (new Random().nextInt(5) <= 3) {
                student.setSgender("male");
                student.setSname(ChineseNameUtil.getName(true, i % 2 + 2));
            } else {
                student.setSgender("female");
                student.setSname(ChineseNameUtil.getName(false, i % 2 + 3));
            }
            student.setClazz(clazz);
            student.setBirthday(LocalDate.now());
            list.add(student);
            System.out.println(student.toString());
        }
        clazz.setStudentList(list);
//        clazzRepo.save(clazz);
        studentRepo.saveAll(list);
    }


    /**
     * 根据c_name获取班级里的所有学生
     */
    @Test
    void allStudentsFromClazz() {
//        Set<Student> studentSet = studentRepo.findByClazzCname("1班");
//        for (Student s : studentSet) {
//            System.out.println(s);
//        }
        //是上面四行的简写
        //这个不加排序也自动排序
        studentRepo.findByClazzCname("1班")
//                .stream()
//                .sorted(Comparator.comparingInt(Student::getSnumber))
                .forEach(System.out::println);

/*
//      clazzRepo.findAll()
//                .stream()
//                .map(Clazz::getCname)
//                .filter(p->p.equals("1班"))
//                .forEach(System.out::println);
*/
    }

    /**
     * 根据g_name获取年级里的所有学生(使用studentService中被实现的方法)
     */
    @Test
    void allStudentsFromGrade() throws Exception {
        //流的方式将其按照学号（s_number）排序输出
        //这个需要排序
        studentService.findByGradeGname("1年级")
                .stream()
                .sorted(Comparator.comparingInt(Student::getSnumber))
                .forEach(System.out::println);
    }


    /**
     * 增加年级（使用Jpa的save()）
     */
    @Test
    void addGrade() {
        Grade grade = new Grade();
        grade.setGname("3年级");
        gradeRepo.save(grade);
        System.out.println(grade);
    }


    @Test
    void addClazz() {
//        Map<String, List<Clazz>> map =clazzService.classify();
//        System.out.println(map);
//        Clazz clazz = clazzRepo.findByCid(1).get();
//        System.out.println(clazz);
        Grade grade = gradeRepo.findByGname("2年级").orElseThrow(
                () -> new ExceptionIncludingMockitoWarnings("年级不存在或输入有误", new Exception())
        );
        Clazz clazz = new Clazz();
        clazz.setCname("2班");
        clazz.setGrade(grade);
        clazzRepo.save(clazz);
//        Clazz clazz=clazzRepo.findByCid(1).get();
//        System.out.println(clazz);
    }

    @Test
    void testforcgname(){
//        System.out.println(clazzRepo.findByCnameAndGradeGname("1班","1年级"));
//        Clazz clazz = clazzRepo.findByCname("1班").get();
//        clazzRepo.delete(clazz);
//        clazzRepo.flush();
//        clazz.setGrade(gradeRepo.findByGname("2年级").get());
//        clazz.setCname(clazz.getCname());
//        clazz.setCuuid(clazz.getCuuid());
//        System.out.println(clazz.getCuuid());
//        clazzRepo.save(clazz);
    }


}
