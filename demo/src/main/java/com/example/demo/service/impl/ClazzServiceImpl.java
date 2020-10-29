package com.example.demo.service.impl;


import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Grade;
import com.example.demo.dao.dbo.Student;
import com.example.demo.dao.repo.ClazzRepo;
import com.example.demo.dao.repo.GradeRepo;
import com.example.demo.dao.repo.StudentRepo;
import com.example.demo.service.ClazzService;
import com.example.demo.service.GradeService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Tethamo_zzx
 * @date 2020-10-26  上午 10:00
 */
@Service
public class ClazzServiceImpl implements ClazzService {


    ClazzRepo clazzRepo;
    StudentRepo studentRepo;
    GradeRepo gradeRepo;
    GradeService gradeService;

    @Autowired
    public ClazzServiceImpl(ClazzRepo clazzRepo,
                            StudentRepo studentRepo,
                            GradeService gradeService,
                            GradeRepo gradeRepo) {
        this.clazzRepo = clazzRepo;
        this.studentRepo = studentRepo;
        this.gradeService = gradeService;
        this.gradeRepo = gradeRepo;
    }


    public Map<String, Long> sexRatio(String cname) throws Exception {
        Map<String, Long> map = new HashMap<>(10);
        //校验
        if (!existByName(cname)) {
            throw new Exception("不存在该班级");
        }
        Set<Student> studentSet = studentRepo.findByClazzCname(cname);
        long men = studentSet.stream()
                .filter(student -> "male".equals(student.getSgender()))
                .count();
        long women = studentSet.size() - men;
        map.put("men", men);
        map.put("women", women);
        return map;
    }

    @Override
    public String sexRatioOne(String cname) throws Exception {
        Map<String, Long> map = sexRatio(cname);
        long men = map.get("men");
        long women = map.get("women");
        String re;
        if (men >= women) {
            BigDecimal bg = new BigDecimal((double) men / women).setScale(1, RoundingMode.UP);
            re = bg + ":1";
        } else {
            BigDecimal bg = new BigDecimal((double) women / men).setScale(1, RoundingMode.UP);
            re = "1:" + bg;
        }
        return re;
    }

    @Override
    public String sexRatioTwo(String cname) throws Exception {
        Map<String, Long> map = sexRatio(cname);
        long men = map.get("men");
        long women = map.get("women");
        return men + ":" + women;
    }

    @Override
    public Boolean updateClazzWithGrade(String cname, String gname) throws Exception {

//        //  校验有没有这个班级
//        if (StringUtils.isBlank(cname) || existByName(cname)) {
//            throw new Exception("班级id输入有误或不存在");
//        }

        //检验班级
        Clazz clazz = clazzRepo.findByCname(cname)
                .orElseThrow(() -> new Exception("班级有误或不存在"));

        // 校验有没有这个年级
//        if (StringUtils.isBlank(gname) || !gradeService.existByName(gname)) {
//            throw new Exception("年级id输入有误或不存在");
//        }
        Grade grade = gradeRepo.findByGname(gname)
                .orElseThrow(() -> new Exception("年级有误或不存在"));

        // 校验这种情况是否需要更新
//        if (clazzRepo.findByGradeGname(gname)
//                .stream()
//                .map(Clazz::getCname)
//                .anyMatch((e)->e.equals(cname))) { throw new Exception("不需要更新"); }
        if (existByCnameAndGname(cname, gname)) {
            throw new Exception("不需要更新");
        }


        //重置clazz的年级
        clazz.setGrade(grade);

        //
        System.out.println(cname);
        System.out.println(clazz.getGrade().getGname());
        //
        //更新
        clazzRepo.save(clazz);
        return true;
    }

    @Override
    public Map<String, Set<Clazz>> classify() {
        //所有的年级
        List<Grade> gradeList = gradeRepo.findAll();
        Map<String, Set<Clazz>> collect = gradeList.stream()
                .collect(Collectors.toMap(Grade::getGname, Grade::getClazzList));
        return collect;
    }


    @Override
    public Boolean existByName(String cname) {
        Optional<Clazz> optionalClazz = clazzRepo.findByCname(cname);
        return optionalClazz.isPresent();
    }

    @Override
    public Boolean existByCnameAndGname(String cname, String gname) {
        Optional<Clazz> optionalClazz = clazzRepo.findByCnameAndGradeGname(cname, gname);
        return optionalClazz.isPresent();
    }
}
