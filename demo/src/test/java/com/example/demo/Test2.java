package com.example.demo;


import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Grade;

import com.example.demo.service.ClazzService;
import com.example.demo.service.GradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Map;


/**
 * @author Tethamo_zzx
 * @date 2020-10-26  下午 01:31
 */
@SpringBootTest
public class Test2 {
    ClazzService clazzService;
    GradeService gradeService;

    @Autowired
    public Test2(ClazzService clazzService,
                 GradeService gradeService) {
        this.clazzService = clazzService;
        this.gradeService = gradeService;
    }

    @Test
    void test1(){
        boolean b =  clazzService.updateClazzWithGrade(6,3);
        System.out.println(b);
    }

    @Test
    void test2(){
        System.out.println("该班的男女比例为："+clazzService.sexRatio(2));
    }

    @Test
    void test3(){

        Map<String, List<Clazz>> map =clazzService.classifyByClazz();
//        System.out.println(map);


//        List<Grade> gradeList = gradeService.classifyByGrade();
//        for(Grade g:gradeList)
//            System.out.println(g);

    }
}
