package com.klef.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.practice.model.Report;
import com.klef.practice.service.ReportService;



@RestController
@RequestMapping("/reportapi")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;
    
    @GetMapping("/")
    public String home() {
        return "Citizen Weather Report API - Jenkins Full Stack Deployment Demo";
    }

    @PostMapping("/add")
    public ResponseEntity<Report> addReport(@RequestBody Report report) {
        Report savedReport = reportService.addReport(report);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getReportById(@PathVariable int id) {
        Report report = reportService.getReportById(id);
        if (report != null) {
            return new ResponseEntity<>(report, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Report with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateReport(@RequestBody Report report) {
        Report existing = reportService.getReportById(report.getId());
        if (existing != null) {
            Report updatedReport = reportService.updateReport(report);
            return new ResponseEntity<>(updatedReport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Report with ID " + report.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable int id) {
        Report existing = reportService.getReportById(id);
        if (existing != null) {
            reportService.deleteReportById(id);
            return new ResponseEntity<>("Report with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Report with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
