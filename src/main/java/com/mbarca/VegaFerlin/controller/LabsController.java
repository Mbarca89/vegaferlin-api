package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.dto.request.LabsRequestDto;
import com.mbarca.VegaFerlin.dto.response.LabsResponseDto;
import com.mbarca.VegaFerlin.mapper.LabsMapper;
import com.mbarca.VegaFerlin.model.Labs;
import com.mbarca.VegaFerlin.service.FileStorageService;
import com.mbarca.VegaFerlin.service.LabsService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/labs")
public class LabsController {

    @Autowired
    private LabsService labsService;

    private final FileStorageService fileStorageService;

    public LabsController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @PostMapping("/upload")
    public ResponseEntity<?> updateLabFiles(@RequestParam Long labId, @RequestParam("files") MultipartFile[] files, @RequestParam("labType") String labType, @RequestParam("patientId") Long patientId) {
        labsService.uploadLab(patientId, files, labType, labId);
        return ResponseEntity.status(HttpStatus.OK).body("Archivo cargado correctamente para " + labType);
    }

    @GetMapping("/getLabs")
    public ResponseEntity<?> getAllLabsByPatientId(@RequestParam Long patientId) {
        Labs labs = labsService.getAllLabsByPatientId(patientId);
        LabsResponseDto labsResponse = LabsMapper.INSTANCE.toDto(labs);
        return ResponseEntity.status(HttpStatus.OK).body(labsResponse);
    }

    @GetMapping("/downloadLab")
    public ResponseEntity<?> downloadLab(@RequestParam String filePath) {
        Resource file = fileStorageService.loadAsResource(filePath);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLabs(@RequestParam Long patientId, @RequestBody LabsRequestDto labsRequestDto) {
        try {
            Labs labs = LabsMapper.INSTANCE.toLabs(labsRequestDto);
            labsService.updateLabs(patientId, labs);
            return ResponseEntity.status(HttpStatus.OK).body("Observaciones añadidas correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}

