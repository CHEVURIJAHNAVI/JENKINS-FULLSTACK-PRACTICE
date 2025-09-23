package com.klef.practice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.practice.model.Report;
import com.klef.practice.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report addReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public Report getReportById(int id) {
        Optional<Report> opt = reportRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public void deleteReportById(int id) {
        reportRepository.deleteById(id);
    }
}

