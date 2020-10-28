package com.example.demo.service.impl;

import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Grade;
import com.example.demo.dao.repo.GradeRepo;
import com.example.demo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author Tethamo_zzx
 * @date 2020-10-25  下午 04:37
 */
@Service
public class GradeServiceImpl implements GradeService {

    GradeRepo gradeRepo;

    @Autowired
    public GradeServiceImpl(GradeRepo gradeRepo) {
        this.gradeRepo = gradeRepo;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Grade> classifyByGrade() {
        List<Grade> gradeList = gradeRepo.findAll();
        //
        //
        return gradeList;
    }

    @Override
    public Boolean existById(Integer g_id) {
        Optional<Grade> optionalClazz = gradeRepo.findById(g_id);
        return optionalClazz.isPresent();
    }
}
