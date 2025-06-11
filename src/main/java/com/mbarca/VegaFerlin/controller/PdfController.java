package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.model.Patient;
import com.mbarca.VegaFerlin.service.PatientPDFExportService;
import com.mbarca.VegaFerlin.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientPDFExportService exportService;

    @GetMapping("/patient/{id}")
    public ResponseEntity<byte[]> exportPatientPdf(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        byte[] pdfBytes = exportService.generatePatientPdf(patient);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=paciente_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}

