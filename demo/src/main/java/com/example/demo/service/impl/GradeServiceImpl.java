package com.example.demo.service.impl;

import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Grade;
import com.example.demo.dao.repo.GradeRepo;
import com.example.demo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
     * @return gl
     */
    @Override
    public Map<String, Set<Clazz>> classify() {
        return gradeRepo.findAll()
                .stream()
                .collect(Collectors.toMap(Grade::getGname,Grade::getClazzList));
    }

    @Override
    public Boolean existByName(String gname) {
        Optional<Grade> optionalClazz = gradeRepo.findByGname(gname);
        return optionalClazz.isPresent();
    }
}
