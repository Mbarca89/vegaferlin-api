package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.SurgicalProtocol;
import com.mbarca.VegaFerlin.repository.SurgicalProtocolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurgicalProtocolService {
    @Autowired
    SurgicalProtocolRepository surgicalProtocolRepository;

    public void createSurgicalProtocol (Long patientId, SurgicalProtocol surgicalProtocol) {
        surgicalProtocolRepository.save(surgicalProtocol);
    }

    public List<SurgicalProtocol> getSurgicalProtocolByPatientId(Long patientId) {
        Optional<List<SurgicalProtocol>> surgicalProtocolsOptional = surgicalProtocolRepository.findByPatientId(patientId);
        if(surgicalProtocolsOptional.isPresent()) {
            return surgicalProtocolsOptional.get();
        } else {
            throw new NotFoundException("Potocolo quirurgico no encontrado");
        }
    }

    public SurgicalProtocol getSurgicalProtocolById(Long id) {
        Optional<SurgicalProtocol> surgicalProtocolsOptional = surgicalProtocolRepository.findById(id);
        if(surgicalProtocolsOptional.isPresent()) {
            return surgicalProtocolsOptional.get();
        } else {
            throw new NotFoundException("Potocolo quirurgico no encontrado");
        }
    }
}
