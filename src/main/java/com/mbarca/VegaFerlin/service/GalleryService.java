package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.domain.Images;
import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.Gallery;
import com.mbarca.VegaFerlin.model.Patient;
import com.mbarca.VegaFerlin.repository.GalleryRepository;
import com.mbarca.VegaFerlin.repository.PatientRepository;
import com.mbarca.VegaFerlin.utils.ImageCompressor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class GalleryService {
    @Autowired
    GalleryRepository galleryRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    ImageCompressor imageCompressor;
    public Map<String, List<String>> getAllThumbsByPatientId(Long patientId, String study) {
        Optional<Gallery> galleryOptional = galleryRepository.findByPatientId(patientId);
        if (galleryOptional.isPresent()) {
            Gallery gallery = galleryOptional.get();
            Map<String, List<String>> thumbsMap = new HashMap<>();
            switch (study) {
                case "Extraorales":
                    thumbsMap.put("extraoralFront_thumb", generateUrlList(gallery.getExtraoralFront_thumb(), false));
                    thumbsMap.put("extraoralMax_thumb", generateUrlList(gallery.getExtraoralMax_thumb(), false));
                    thumbsMap.put("extraoralLeft_thumb", generateUrlList(gallery.getExtraoralLeft_thumb(), false));
                    thumbsMap.put("extraoralRight_thumb", generateUrlList(gallery.getExtraoralRight_thumb(), false));
                    break;
                case "Intraorales":
                    thumbsMap.put("intraoralFront_thumb", generateUrlList(gallery.getIntraoralFront_thumb(), false));
                    thumbsMap.put("intraoralBlackBackground_thumb", generateUrlList(gallery.getIntraoralBlackBackground_thumb(),false));
                    thumbsMap.put("intraoralLeft_thumb", generateUrlList(gallery.getIntraoralLeft_thumb(),false));
                    thumbsMap.put("intraoralRight_thumb", generateUrlList(gallery.getIntraoralRight_thumb(), false));
                    break;
                case "Arco dentario":
                    thumbsMap.put("arcTop_thumb", generateUrlList(gallery.getArcTop_thumb(), false));
                    thumbsMap.put("arcBottom_thumb", generateUrlList(gallery.getArcBottom_thumb(), false));
                    break;
                case "Zona a tratar":
                    thumbsMap.put("oclusal_thumb", generateUrlList(gallery.getOclusal_thumb(), false));
                    thumbsMap.put("vestibular_thumb", generateUrlList(gallery.getVestibular_thumb(), false));
                    break;
                case "Digitalización":
                    thumbsMap.put("panoramic_thumb", generateUrlList(gallery.getPanoramic_thumb(), false));
                    thumbsMap.put("xray_thumb", generateUrlList(gallery.getXray_thumb(), false));
                    break;
                default:
                    throw new NotFoundException("Estudio no encontrado");
            }
            return thumbsMap;
        } else {
            throw new NotFoundException("Galería no encontrada");
        }
    }

    public Map<String, List<String>> getSingleGalleryByPatientId(Long patientId, String study) {
        Optional<Gallery> galleryOptional = galleryRepository.findByPatientId(patientId);
        if (galleryOptional.isPresent()) {
            Gallery gallery = galleryOptional.get();
            Map<String, List<String>> galleryMap = new HashMap<>();

            String baseName = getBaseName(study);
            if (baseName == null) {
                throw new NotFoundException("Estudio no encontrado");
            }

            try {
                Method getThumbMethod = Gallery.class.getMethod("get" + baseName + "_thumb");
                Method getFullMethod = Gallery.class.getMethod("get" + baseName);
                Method getHDMethod = Gallery.class.getMethod("get" + baseName + "_HD");


                List<String> thumbnails = (List<String>) getThumbMethod.invoke(gallery);
                List<String> originals = (List<String>) getFullMethod.invoke(gallery);
                List<String> hdImages = (List<String>) getHDMethod.invoke(gallery);
                galleryMap.put("thumbnail", generateUrlList(thumbnails, false));
                galleryMap.put("original", generateUrlList(originals, false));
                galleryMap.put("HD", generateUrlList(hdImages, true));

            } catch (Exception e) {
                throw new RuntimeException("Error al obtener la galería " + e.getMessage(), e);
            }

            return galleryMap;
        } else {
            throw new NotFoundException("Galería no encontrada");
        }
    }

    public void uploadImage(Long patientId, String imageName, MultipartFile file) throws Exception {
        Optional<Gallery> galleryOptional = galleryRepository.findByPatientId(patientId);
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        String patientName;
        if (patientOptional.isPresent()) {
            patientName = patientOptional.get().getSurname() + "_" + patientOptional.get().getName();
            patientName = patientName.replaceAll("[^a-zA-Z0-9_-]", "_");
        } else {
            throw new RuntimeException("El paciente no existe!");
        }
        if (galleryOptional.isPresent()) {
            Gallery gallery = galleryOptional.get();
            Images images = imageCompressor.compressImage(file.getBytes(), true, imageName, patientName);
            List<String> thumbnails = new ArrayList<>();
            List<String> fullImages = new ArrayList<>();
            List<String> HDImages = new ArrayList<>();
            thumbnails.add(images.getPaths().get(1));
            fullImages.add(images.getPaths().getFirst());
            HDImages.add(images.getPaths().getLast());

            String baseName = getBaseName(imageName);
            if (baseName == null) {
                throw new IllegalArgumentException("Error al cargar la imagen");
            }

            try {
                Method getThumbMethod = Gallery.class.getMethod("get" + baseName + "_thumb");
                Method getFullMethod = Gallery.class.getMethod("get" + baseName);
                Method getHDMethod = Gallery.class.getMethod("get" + baseName + "_HD");

                Method setThumbMethod = Gallery.class.getMethod("set" + baseName + "_thumb", List.class);
                Method setFullMethod = Gallery.class.getMethod("set" + baseName, List.class);
                Method setHDMethod = Gallery.class.getMethod("set" + baseName + "_HD", List.class);

                thumbnails.addAll((List<String>) getThumbMethod.invoke(gallery));
                fullImages.addAll((List<String>) getFullMethod.invoke(gallery));
                HDImages.addAll((List<String>) getHDMethod.invoke(gallery));

                setThumbMethod.invoke(gallery, thumbnails);
                setFullMethod.invoke(gallery, fullImages);
                setHDMethod.invoke(gallery, HDImages);

            } catch (Exception e) {
                throw new RuntimeException("Error al actualizar la galería", e);
            }

            galleryRepository.save(gallery);
        } else {
            throw new RuntimeException("No se encontró la galeria del paciente");
        }
    }

    private String getBaseName(String imageName) {
        switch (imageName) {
            case "Extraoral-frente":
                return "ExtraoralFront";
            case "Extraoral-izquierdo":
                return "ExtraoralLeft";
            case "Extraoral-derecho":
                return "ExtraoralRight";
            case "Extraoral-sonrisa-máxima":
                return "ExtraoralMax";
            case "Intraoral-frente":
                return "IntraoralFront";
            case "Intraoral-fondo-negro":
                return "IntraoralBlackBackground";
            case "Intraoral-izquierdo":
                return "IntraoralLeft";
            case "Intraoral-derecho":
                return "IntraoralRight";
            case "Arco-dentario-superior":
                return "ArcTop";
            case "Arco-dentario-inferior":
                return "ArcBottom";
            case "Zona-a-tratar-oclusal":
                return "Oclusal";
            case "Zona-a-tratar-vestibular":
                return "Vestibular";
            case "Digitalización-panorámica":
                return "Panoramic";
            case "Digitalización-radiografías":
                return "Xray";
            default:
                return null;
        }
    }

    private List<String> generateUrlList(List<String> filenames, Boolean download) {
        List<String> urls = new ArrayList<>();
        for (String filepath : filenames) {
            // Convertir la ruta del archivo de Windows a una ruta relativa
            Path path = Paths.get(filepath);

            // Extraer las partes de la ruta
            Path fileName = path.getFileName(); // Extrae el nombre del archivo
            Path parentDir = path.getParent(); // Directorio principal (Imagenes)
            Path studyDir = parentDir.getParent(); // Directorio del estudio
            Path patientDir = studyDir.getParent(); // Directorio del paciente

            // Convertir las rutas a Strings y codificar para URL
            String nombrePaciente = encodeURIComponent(patientDir.getFileName().toString()); // Nombre del paciente
            String nombreEstudio = encodeURIComponent(studyDir.getFileName().toString()); // Nombre del estudio
            String extraDir = encodeURIComponent(parentDir.getFileName().toString()); // Carpeta adicional
            String filename = encodeURIComponent(fileName.toString()); // Nombre del archivo

            // Construir la URL completa
            if(!download) {
            String url = String.format("http://localhost:8080/api/images/%s/%s/%s/%s",
                    nombrePaciente, nombreEstudio, extraDir, filename);
            urls.add(url);
            } else {
                String url = String.format("http://localhost:8080/api/images/download/%s/%s/%s/%s",
                        nombrePaciente, nombreEstudio, extraDir, filename);
                urls.add(url);
            }
        }
        return urls;
    }

    private String encodeURIComponent(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8")
                    .replace("+", "%20") // Reemplazar espacios por %20
                    .replace("*", "%2A")
                    .replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error al codificar la URL", e);
        }
    }
}
