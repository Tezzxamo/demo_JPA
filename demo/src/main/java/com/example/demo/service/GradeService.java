package com.example.demo.service;

import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Grade;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Tethamo_zzx
 * @date 2020-10-25  下午 04:37
 */
public interface GradeService {

    /**
     * 通过年级来进行分类
     */
    Map<String, Set<Clazz>> classify();

    Boolean existByName(String gname);
}
