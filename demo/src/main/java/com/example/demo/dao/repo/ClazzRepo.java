package com.example.demo.dao.repo;

import com.example.demo.dao.dbo.Clazz;
import com.example.demo.dao.dbo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Tethamo_zzx
 * @date 2020-10-26  上午 09:59
 */
public interface ClazzRepo extends JpaRepository<Clazz, UUID> {

    /**
     * 根据年级找到所有的班级
     * @param gname 年级id
     * @return 所有的班级
     */
    Set<Clazz> findByGradeGname(String gname);

    Optional<Clazz> findByCname(String cname);

    Optional<Clazz> findByCnameAndGradeGname(String cname,String gname);

}
