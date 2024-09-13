package com.mbarca.VegaFerlin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Value("${file.storage.location}")
    private String storageLocation;

    @GetMapping("/{patient}/{study}/{extraDir}/{filename:.+}")
    public ResponseEntity<Resource> getImage(
            @PathVariable String patient,
            @PathVariable String study,
            @PathVariable String extraDir,
            @PathVariable String filename) {
        try {
            // Construir la ruta completa del archivo
            Path filePath = Paths.get(storageLocation)
                    .resolve(patient)
                    .resolve(study)
                    .resolve(extraDir)
                    .resolve(filename)
                    .normalize();

            // Crear el recurso
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace();  // Agregar log para detalles del error
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/download/{patient}/{study}/{extraDir}/{filename:.+}")
    public ResponseEntity<Resource> downloadImage(
            @PathVariable String patient,
            @PathVariable String study,
            @PathVariable String extraDir,
            @PathVariable String filename) {
        try {
            // Construir la ruta completa del archivo
            Path filePath = Paths.get(storageLocation)
                    .resolve(patient)
                    .resolve(study)
                    .resolve(extraDir)
                    .resolve(filename)
                    .normalize();

            // Crear el recurso
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace();  // Agregar log para detalles del error
            return ResponseEntity.internalServerError().build();
        }
    }
}