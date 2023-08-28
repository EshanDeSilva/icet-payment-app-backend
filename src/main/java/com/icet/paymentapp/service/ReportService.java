package com.icet.paymentapp.service;

import com.icet.paymentapp.entity.Report;
import org.springframework.http.ResponseEntity;

public interface ReportService {
    ResponseEntity<byte[]> generateReport(Report report);
}
