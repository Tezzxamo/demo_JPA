package com.example.demo.service;


import com.example.demo.dao.dbo.Clazz;

import java.util.Map;
import java.util.Set;

/**
 * @author Tethamo_zzx
 * @date 2020-10-26  上午 10:00
 */
public interface ClazzService {


    String sexRatioOne(String cname) throws Exception;

    String sexRatioTwo(String cname) throws Exception;



    Boolean updateClazzWithGrade(String cname,String gname) throws Exception;

    Map<String, Set<Clazz>> classify();

    Boolean existByName(String cname);

    Boolean existByCnameAndGname(String cname,String gname);
}
