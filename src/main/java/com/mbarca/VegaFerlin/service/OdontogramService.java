package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.dto.request.OdontogramRequestDto;
import com.mbarca.VegaFerlin.dto.response.OdontogramResponseDto;
import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.Odontogram;
import com.mbarca.VegaFerlin.repository.OdontogramRepository;
import com.mbarca.VegaFerlin.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OdontogramService {
    @Autowired
    private OdontogramRepository odontogramRepository;


    public void createOdontogram (Long patientId, Odontogram odontogram) {
        odontogram.setOdontogramDate(new Date());
        odontogramRepository.save(odontogram);
    }

    public Odontogram getLastOdontogram (Long patientId) {
        Optional<Odontogram> odontogramOptional = odontogramRepository.findTopByPatientIdOrderByOdontogramDateDesc(patientId);
        if(odontogramOptional.isPresent()) {
            return odontogramOptional.get();
        } else {
            throw new NotFoundException("Aún no se ha guardado ningún odontograma");
        }
    }

    public Odontogram getOdontogramById (Long id) {
        return odontogramRepository.findById(id).orElseThrow(() -> new NotFoundException("Odontograma no encontrado"));
    }

    public List<Odontogram> getOdontrogramsByPatientId (Long patientId) {
        Optional<List<Odontogram>> odontogramsOptional = odontogramRepository.findByPatientId(patientId);
        if(odontogramsOptional.isPresent()) {
            return odontogramsOptional.get();
        } else {
            throw new NotFoundException("Hubo un error al obtener los odontogramas");
        }
    }
}
