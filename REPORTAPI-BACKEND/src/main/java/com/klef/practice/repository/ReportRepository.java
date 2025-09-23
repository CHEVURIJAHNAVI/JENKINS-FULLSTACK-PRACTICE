package com.klef.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.practice.model.Report;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> 
{
    
    Report findByContact(String contact);
    List<Report> findByLocation(String location);
    List<Report> findByCondition(String condition);
    List<Report> findByDate(String date);
}

