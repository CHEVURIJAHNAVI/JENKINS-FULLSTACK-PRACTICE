package com.klef.practice.service;

import java.util.List;

import com.klef.practice.model.Report;


public interface ReportService {
    Report addReport(Report report);
    List<Report> getAllReports();
    Report getReportById(int id);
    Report updateReport(Report report);
    void deleteReportById(int id);
}
