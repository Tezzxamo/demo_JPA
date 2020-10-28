package com.example.demo.service;


import com.example.demo.dao.dbo.Clazz;

import java.util.List;
import java.util.Map;

/**
 * @author Tethamo_zzx
 * @date 2020-10-26  上午 10:00
 */
public interface ClazzService {

    /**
     * 一个班级的男女比例
     * @param clazz_id 班级id
     * @return 改班级的男女比例
     */
    String sexRatio(Integer clazz_id);


    /**
     * 调整一个班级的所在年级
     * @param c_id 班级id
     * @param g_id 年级id
     * @return 返回调整成功与否
     * @throws Exception 抛出错误信息
     */
    Boolean updateClazzWithGrade(Integer c_id,Integer g_id) throws Exception;

    Map<String, List<Clazz>> classifyByClazz();

    Boolean existById(Integer c_id);
}
