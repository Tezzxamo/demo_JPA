package com.example.demo;


import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Grade;

import com.example.demo.service.ClazzService;
import com.example.demo.service.GradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
    void test1() {
        boolean b = false;
        try {
            b = clazzService.updateClazzWithGrade("2班", "1年级");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(b);
    }

    @Test
    void test2() {
        try {
            System.out.println("该班的男女比例为：" + clazzService.sexRatioOne("2班"));
            System.out.println("该班的男女比例为：" + clazzService.sexRatioTwo("2班"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test3() {
        Map<String, Set<Clazz>> map = clazzService.classify();
        System.out.println(map);


        Map<String, Set<Clazz>> gradeMap = gradeService.classify();
        for (String s : gradeMap.keySet()) {
            System.out.println(s + ":");
            gradeMap.get(s).stream()
                    .sorted(Comparator.comparing(Clazz::getCname))
                    .forEach(x -> System.out.println("    " + x));
        }

    }
}
