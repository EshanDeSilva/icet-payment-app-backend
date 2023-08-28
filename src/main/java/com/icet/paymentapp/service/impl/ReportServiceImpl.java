package com.icet.paymentapp.service.impl;

import com.icet.paymentapp.entity.Report;
import com.icet.paymentapp.service.ReportService;
import net.sf.jasperreports.engine.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

        private ResponseEntity<byte[]> getJasperPrint(Report report) throws FileNotFoundException, JRException {
            Map parameters = new HashMap<>();
            parameters.put("course",report.getCourse());
            parameters.put("full_name",report.getFullName());
            parameters.put("nic",report.getNic());
            parameters.put("payment_id",report.getPaymentId());
            parameters.put("date",report.getDate());
            parameters.put("payment_type",report.getPaymentType().toString());
            parameters.put("amount",report.getAmount());

            JasperPrint jasperPrint = JasperFillManager.fillReport
                    (
                            JasperCompileManager.compileReport(
                                    ResourceUtils.getFile("src/main/resources/jasper/payment-receipt.jrxml")
                                            .getAbsolutePath()) // path of the jasper report
                            , parameters // dynamic parameters
                            , new JREmptyDataSource()
                    );

            HttpHeaders headers = new HttpHeaders();
            //set the PDF format
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("iCET", report.getPaymentId()+".pdf");
            //create the report in PDF format
            File file = new File("src/main/resources/reports/");

            JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath()+"/" +report.getPaymentId()+".pdf");
            return new ResponseEntity
                    (JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);

        }

        @Override
        public ResponseEntity<byte[]> generateReport(Report report) {
            try {
                return getJasperPrint(report);
            } catch (FileNotFoundException |JRException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

}
