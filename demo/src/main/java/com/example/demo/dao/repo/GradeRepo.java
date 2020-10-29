package com.example.demo.dao.repo;

import com.example.demo.dao.dbo.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


/**
 * @author Tethamo_zzx
 * @date 2020-10-25  下午 04:36
 */
public interface GradeRepo extends JpaRepository<Grade, UUID> {

    Optional<Grade> findByGname(String gname);

    Optional<Grade> findByGuuid(UUID guuid);
}
