package com.project.back.repository;

import com.project.back.entity.AuthNumberEntity;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuthNumberRepository extends JpaRepository<AuthNumberEntity, String> 
{
    boolean existsByTelNumberAndAuthNumber(String telNumber, String authNumber);
}
/* 분석 완료 */