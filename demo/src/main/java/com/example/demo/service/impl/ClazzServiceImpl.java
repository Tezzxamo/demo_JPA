package com.example.demo.service.impl;


import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Grade;
import com.example.demo.dao.dbo.Student;
import com.example.demo.dao.repo.ClazzRepo;
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
    GradeService gradeService;

    @Autowired
    public ClazzServiceImpl(ClazzRepo clazzRepo, StudentRepo studentRepo,GradeService gradeService) {
        this.clazzRepo = clazzRepo;
        this.studentRepo = studentRepo;
        this.gradeService = gradeService;
    }

    @Override
    public String sexRatio(Integer c_id) {

        //校验


        int men = 0;
        int women = 0;
        Set<Student> studentSet = studentRepo.findByClazzId(c_id);

//        studentSet.stream()
//                .filter()
//                .count()

        for (Student s : studentSet) {
            if ("male".equals(s.getGender())) {
                men++;
            } else {
                women++;
            }
        }
        String re;
        if (men >= women) {
            BigDecimal bg = new BigDecimal((double) men / women).setScale(1, RoundingMode.UP);
            re = bg + ":1";
        } else {
            BigDecimal bg = new BigDecimal((double) women / men).setScale(1, RoundingMode.UP);
            re = "1:" + bg;
        }

//        58:90
        return re;
    }

    @Override
    public Boolean updateClazzWithGrade(Integer c_id, Integer g_id) throws Exception {

        //  校验有没有这个班级
        if (StringUtils.isBlank(c_id.toString())||existById(c_id)){
            throw new Exception("班级id输入有误或不存在");
        }
        // 校验有没有这个年级
        if (StringUtils.isBlank(g_id.toString())||gradeService.existById(g_id)){
            throw new Exception("年级id输入有误或不存在");
        }
        // 校验这种情况是否需要更新


        //更新
        Optional<Clazz> optionalClazz = clazzRepo.findById(c_id);

//        Clazz clazz1 = clazzRepo.findById(c_id)
//                .orElseThrow(() -> new Exception(""));
        Clazz clazz = new Clazz();
        Grade grade = new Grade();
        grade.setId(g_id);
        if (optionalClazz.isPresent()) {
            clazz = optionalClazz.get();
        }

        //LUO JI BU WANZHENG


        clazz.setGrade(grade);
        System.out.println(c_id);
        System.out.println(clazz.getId());
        clazzRepo.save(clazz);
        //确认
        optionalClazz = clazzRepo.findById(c_id);
        if (optionalClazz.isPresent()) {
            clazz = optionalClazz.get();
        }
        return clazz.getGrade().getId() == g_id;
    }

    @Override
    public Map<String, List<Clazz>> classifyByClazz() {
        List<Clazz> clazzList = clazzRepo.findAll();
        Map<String, List<Clazz>> gradeMap = new HashMap<>();
        List<Clazz> valueClazz = new ArrayList<>();
        int g_id = -1;
        String key = "";
        for (int i = 0; i < clazzList.size(); i++) {
            Clazz clazz = clazzList.get(i);
            if (i == 0) {
                g_id = clazz.getGrade().getId();
                key = clazz.getGrade().getGName();
            } else if (g_id != clazz.getGrade().getId()) {
                valueClazz= new ArrayList<>();
                g_id = clazz.getGrade().getId();
                key = clazz.getGrade().getGName();
            }
            System.out.println(key+" —————对应————— 班级："+clazz.getId());
            valueClazz.add(clazz);
            gradeMap.put(key, valueClazz);
        }
        for(String k:gradeMap.keySet()){
            List<Clazz> lc = gradeMap.get(k);
            System.out.println(k+" —拥有— ");
            for (Clazz c:lc){
                System.out.println("     —————— "+c.getId()+"班");
            }
            System.out.println();
        }


        return gradeMap;
    }

    @Override
    public Boolean existById(Integer c_id) {
        Optional<Clazz> optionalClazz = clazzRepo.findById(c_id);
        return optionalClazz.isPresent();
    }
}
