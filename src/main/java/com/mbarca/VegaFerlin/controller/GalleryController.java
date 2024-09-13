package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin
public class GalleryController {
    @Autowired
    GalleryService galleryService;

    @PostMapping("/upload")
    public ResponseEntity<?> updateFiles(@RequestParam Long patientId, @RequestParam("files") MultipartFile files, @RequestParam("imageName") String imageName) throws Exception {
        galleryService.uploadImage(patientId, imageName, files);
        return ResponseEntity.status(HttpStatus.OK).body("Imagen cargada correctamente para " + imageName);
    }

    @GetMapping("/thumbs")
    public ResponseEntity<Map<String, List<String>>> getAllThumbsByPatientId(@RequestParam Long patientId, @RequestParam String study) {
        Map<String, List<String>> thumbs = galleryService.getAllThumbsByPatientId(patientId, study);
        return ResponseEntity.status(HttpStatus.OK).body(thumbs);
    }

    @GetMapping("/singleGallery")
    public ResponseEntity<Map<String, List<String>>> getSingleGalleryByPatientId(@RequestParam Long patientId, @RequestParam String study) {
        Map<String, List<String>> gallery = galleryService.getSingleGalleryByPatientId(patientId, study);
        return ResponseEntity.status(HttpStatus.OK).body(gallery);
    }
}
