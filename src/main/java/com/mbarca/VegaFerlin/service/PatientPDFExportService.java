package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.model.DentalEvaluation;
import com.mbarca.VegaFerlin.model.MedicalHistory;
import com.mbarca.VegaFerlin.model.Patient;
import com.mbarca.VegaFerlin.model.SurgicalProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import org.thymeleaf.context.Context;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

@Service
public class PatientPDFExportService {

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] generatePatientPdf(Patient patient) {
        Context context = new Context(Locale.getDefault());
        context.setVariable("patient", patient);
        context.setVariable("medicalHistory", patient.getMedicalHistory());
        context.setVariable("healthQuestionnaire", patient.getHealthQuestionnaire());
        context.setVariable("dentalEvaluation", patient.getDentalEvaluation());
        context.setVariable("activityLogs", patient.getActivityLogs());
        context.setVariable("workPlans", patient.getWorkPlans());
        context.setVariable("surgicalProtocols", patient.getSurgicalProtocols());
        String htmlContent;
        try {
            htmlContent = templateEngine.process("patient-pdf", context);
        } catch (TemplateProcessingException e) {
            e.printStackTrace(); // o logger
            throw new RuntimeException("Error procesando la plantilla HTML", e);
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }
}
