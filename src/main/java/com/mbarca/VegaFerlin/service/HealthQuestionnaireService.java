package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.HealthQuestionnaire;
import com.mbarca.VegaFerlin.repository.HealthQuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HealthQuestionnaireService {
    @Autowired
    HealthQuestionnaireRepository healthQuestionnaireRepository;
    public HealthQuestionnaire getByPatientId(Long patientId) {
        Optional<HealthQuestionnaire> healthQuestionnaireOptional = healthQuestionnaireRepository.findByPatientId(patientId);
        if(healthQuestionnaireOptional.isPresent()) {
        return healthQuestionnaireOptional.get();
        } else {
            throw new NotFoundException("Cuestionario médico no encontrado");
        }
    }
    public void updateHealthQuestionnaire(Long patientId, HealthQuestionnaire newHealthQuestionnaire) {
        Optional<HealthQuestionnaire> healthQuestionnaireOptional = healthQuestionnaireRepository.findByPatientId(patientId);
        if (healthQuestionnaireOptional.isPresent()) {
            HealthQuestionnaire healthQuestionnaire = healthQuestionnaireOptional.get();
            healthQuestionnaire.setHealthAlteration(newHealthQuestionnaire.isHealthAlteration());
            healthQuestionnaire.setHealthChange(newHealthQuestionnaire.isHealthChange());
            healthQuestionnaire.setLastMedicalCheck(newHealthQuestionnaire.getLastMedicalCheck());
            healthQuestionnaire.setMedicalAttention(newHealthQuestionnaire.isMedicalAttention());
            healthQuestionnaire.setMedicalAttentionReason(newHealthQuestionnaire.getMedicalAttentionReason());
            healthQuestionnaire.setMedicInformation(newHealthQuestionnaire.getMedicInformation());
            healthQuestionnaire.setMajorSurgery(newHealthQuestionnaire.isMajorSurgery());
            healthQuestionnaire.setMajorSurgeryReason(newHealthQuestionnaire.getMajorSurgeryReason());
            healthQuestionnaire.setHospitalized(newHealthQuestionnaire.isHospitalized());
            healthQuestionnaire.setHospitalizedReason(newHealthQuestionnaire.getHospitalizedReason());
            healthQuestionnaire.setHeight(newHealthQuestionnaire.getHeight());
            healthQuestionnaire.setWeight(newHealthQuestionnaire.getWeight());
            healthQuestionnaire.setMaxBloodPressure(newHealthQuestionnaire.getMaxBloodPressure());
            healthQuestionnaire.setMinBloodPressure(newHealthQuestionnaire.getMinBloodPressure());
            healthQuestionnaire.setPulse(newHealthQuestionnaire.getPulse());
            healthQuestionnaire.setGeneralObservations(newHealthQuestionnaire.getGeneralObservations());
            healthQuestionnaire.setHeartAttacks(newHealthQuestionnaire.isHeartAttacks());
            healthQuestionnaire.setRheumatic(newHealthQuestionnaire.isRheumatic());
            healthQuestionnaire.setChestPain(newHealthQuestionnaire.isChestPain());
            healthQuestionnaire.setBreath(newHealthQuestionnaire.isBreath());
            healthQuestionnaire.setAnkle(newHealthQuestionnaire.isAnkle());
            healthQuestionnaire.setPillow(newHealthQuestionnaire.isPillow());
            healthQuestionnaire.setPacemaker(newHealthQuestionnaire.isPacemaker());
            healthQuestionnaire.setBloodPressureProblems(newHealthQuestionnaire.isBloodPressureProblems());
            healthQuestionnaire.setHearthObservations(newHealthQuestionnaire.getHearthObservations());
            healthQuestionnaire.setEpilepsy(newHealthQuestionnaire.isEpilepsy());
            healthQuestionnaire.setFaints(newHealthQuestionnaire.isFaints());
            healthQuestionnaire.setSeizures(newHealthQuestionnaire.isSeizures());
            healthQuestionnaire.setEmotionalAlteration(newHealthQuestionnaire.isEmotionalAlteration());
            healthQuestionnaire.setAlterationsTreatment(newHealthQuestionnaire.isAlterationsTreatment());
            healthQuestionnaire.setNervousObservations(newHealthQuestionnaire.getNervousObservations());
            healthQuestionnaire.setCough(newHealthQuestionnaire.isCough());
            healthQuestionnaire.setTuberculosis(newHealthQuestionnaire.isTuberculosis());
            healthQuestionnaire.setFamilyTuberculosis(newHealthQuestionnaire.isFamilyTuberculosis());
            healthQuestionnaire.setSinusitis(newHealthQuestionnaire.isSinusitis());
            healthQuestionnaire.setAsma(newHealthQuestionnaire.isAsma());
            healthQuestionnaire.setBreathObservations(newHealthQuestionnaire.getBreathObservations());
            healthQuestionnaire.setStomachUlcer(newHealthQuestionnaire.isStomachUlcer());
            healthQuestionnaire.setHepatitis(newHealthQuestionnaire.isHepatitis());
            healthQuestionnaire.setJaundice(newHealthQuestionnaire.isJaundice());
            healthQuestionnaire.setLiver(newHealthQuestionnaire.isLiver());
            healthQuestionnaire.setBloodVomit(newHealthQuestionnaire.isBloodVomit());
            healthQuestionnaire.setDigestiveObservations(newHealthQuestionnaire.getDigestiveObservations());
            healthQuestionnaire.setDiabetis(newHealthQuestionnaire.isDiabetis());
            healthQuestionnaire.setFamilyDiabetis(newHealthQuestionnaire.isFamilyDiabetis());
            healthQuestionnaire.setUrinate(newHealthQuestionnaire.isUrinate());
            healthQuestionnaire.setThirst(newHealthQuestionnaire.isThirst());
            healthQuestionnaire.setHypothyroidism(newHealthQuestionnaire.isHypothyroidism());
            healthQuestionnaire.setHyperthyroidism(newHealthQuestionnaire.isHyperthyroidism());
            healthQuestionnaire.setEndocrineObservations(newHealthQuestionnaire.getEndocrineObservations());
            healthQuestionnaire.setBloodProblems(newHealthQuestionnaire.isBloodProblems());
            healthQuestionnaire.setFamilyBloodProblems(newHealthQuestionnaire.isFamilyBloodProblems());
            healthQuestionnaire.setHemophiliac(newHealthQuestionnaire.isHemophiliac());
            healthQuestionnaire.setAbnormalBlood(newHealthQuestionnaire.isAbnormalBlood());
            healthQuestionnaire.setBloodTransfusion(newHealthQuestionnaire.isBloodTransfusion());
            healthQuestionnaire.setBloodObservation(newHealthQuestionnaire.getBloodObservation());
            healthQuestionnaire.setAnestheticsAlergy(newHealthQuestionnaire.isAnestheticsAlergy());
            healthQuestionnaire.setAntibioticsAlergy(newHealthQuestionnaire.isAntibioticsAlergy());
            healthQuestionnaire.setBarbituratesAlergy(newHealthQuestionnaire.isBarbituratesAlergy());
            healthQuestionnaire.setAnalgesicsAlergy(newHealthQuestionnaire.isAnalgesicsAlergy());
            healthQuestionnaire.setAsthma(newHealthQuestionnaire.isAsthma());
            healthQuestionnaire.setSkin(newHealthQuestionnaire.isSkin());
            healthQuestionnaire.setAlergyObservations(newHealthQuestionnaire.getAlergyObservations());
            healthQuestionnaire.setKidneyProblems(newHealthQuestionnaire.isKidneyProblems());
            healthQuestionnaire.setSyphilis(newHealthQuestionnaire.isSyphilis());
            healthQuestionnaire.setHiv(newHealthQuestionnaire.isHiv());
            healthQuestionnaire.setGenitourinaryObservations(newHealthQuestionnaire.getGenitourinaryObservations());
            healthQuestionnaire.setTumors(newHealthQuestionnaire.isTumors());
            healthQuestionnaire.setQuimio(newHealthQuestionnaire.isQuimio());
            healthQuestionnaire.setXrays(newHealthQuestionnaire.isXrays());
            healthQuestionnaire.setNeoplaciaObservations(newHealthQuestionnaire.getNeoplaciaObservations());
            healthQuestionnaire.setAlcohol(newHealthQuestionnaire.isAlcohol());
            healthQuestionnaire.setSmoker(newHealthQuestionnaire.isSmoker());
            healthQuestionnaire.setSmokeTimes(newHealthQuestionnaire.getSmokeTimes());
            healthQuestionnaire.setHabitsObservations(newHealthQuestionnaire.getHabitsObservations());
            healthQuestionnaire.setAntibiotics(newHealthQuestionnaire.isAntibiotics());
            healthQuestionnaire.setAnticoagulants(newHealthQuestionnaire.isAnticoagulants());
            healthQuestionnaire.setBloodMedicines(newHealthQuestionnaire.isBloodMedicines());
            healthQuestionnaire.setTranquillizers(newHealthQuestionnaire.isTranquillizers());
            healthQuestionnaire.setHormones(newHealthQuestionnaire.isHormones());
            healthQuestionnaire.setAspirines(newHealthQuestionnaire.isAspirines());
            healthQuestionnaire.setBisphosphonates(newHealthQuestionnaire.isBisphosphonates());
            healthQuestionnaire.setOtherMeds(newHealthQuestionnaire.getOtherMeds());
            healthQuestionnaire.setMidicineObservations(newHealthQuestionnaire.getMidicineObservations());
            healthQuestionnaire.setPregnant(newHealthQuestionnaire.isPregnant());
            healthQuestionnaire.setPregnantPosibilities(newHealthQuestionnaire.isPregnantPosibilities());
            healthQuestionnaire.setBreastfeeding(newHealthQuestionnaire.isBreastfeeding());
            healthQuestionnaire.setMenstrual(newHealthQuestionnaire.isMenstrual());
            healthQuestionnaire.setHormonalTreatment(newHealthQuestionnaire.isHormonalTreatment());
            healthQuestionnaire.setMenstrualDisease(newHealthQuestionnaire.isMenstrualDisease());
            healthQuestionnaire.setOtherDiseases(newHealthQuestionnaire.isOtherDiseases());
            healthQuestionnaire.setOtherDiseasesReason(newHealthQuestionnaire.getOtherDiseasesReason());
            healthQuestionnaire.setWomanObservations(newHealthQuestionnaire.getWomanObservations());
            healthQuestionnaire.setMainDentalProblem(newHealthQuestionnaire.getMainDentalProblem());
            healthQuestionnaire.setPain(newHealthQuestionnaire.isPain());
            healthQuestionnaire.setDentalAspect(newHealthQuestionnaire.isDentalAspect());
            healthQuestionnaire.setEatingProblems(newHealthQuestionnaire.isEatingProblems());
            healthQuestionnaire.setHeadache(newHealthQuestionnaire.isHeadache());
            healthQuestionnaire.setSinuses(newHealthQuestionnaire.isSinuses());
            healthQuestionnaire.setPreviousTreatmentProblems(newHealthQuestionnaire.isPreviousTreatmentProblems());
            healthQuestionnaire.setPreviousTreatmentProblemsReason(newHealthQuestionnaire.getPreviousTreatmentProblemsReason());
            healthQuestionnaire.setDentalObservations(newHealthQuestionnaire.getDentalObservations());
            healthQuestionnaireRepository.save(healthQuestionnaire);
        } else {
            throw new NotFoundException("Cuestionario médico no encontrado");
        }
    }

}
