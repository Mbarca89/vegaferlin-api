package com.mbarca.VegaFerlin.aspect;

import com.mbarca.VegaFerlin.model.ActivityLog;
import com.mbarca.VegaFerlin.model.Patient;
import com.mbarca.VegaFerlin.repository.ActivityLogRepository;
import com.mbarca.VegaFerlin.repository.UserRepository;
import com.mbarca.VegaFerlin.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class ActivityLogAspect {

    @Autowired
    private ActivityLogRepository activityLogRepository;
    @Autowired
    private UserService userService;

    @AfterReturning("execution(* com.mbarca.VegaFerlin.service.DentalEvaluationService.*(..)) || " +
            "execution(* com.mbarca.VegaFerlin.service.DentalPredictionService.*(..)) || " +
            "execution(* com.mbarca.VegaFerlin.service.GalleryService.*(..)) ||" +
            "execution(* com.mbarca.VegaFerlin.service.HealthQuestionnaireService.*(..)) ||" +
            "execution(* com.mbarca.VegaFerlin.service.LabsService.*(..)) ||" +
            "execution(* com.mbarca.VegaFerlin.service.MedicalHistoryService.*(..)) || " +
            "execution(* com.mbarca.VegaFerlin.service.WorkPlanService.*(..)) || " +
            "execution(* com.mbarca.VegaFerlin.service.SurgicalProtocolService.*(..)) || " +
            "execution(* com.mbarca.VegaFerlin.service.PatientService.*(..))")
    public void logActivity(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String activity = "";
        Long patientId = null;
        boolean save = false;

        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof Long) {
                patientId = (Long) arg;
                break;
            }
        }

        Patient patient = new Patient();
        patient.setId(patientId);
        switch (joinPoint.getTarget().getClass().getSimpleName()) {
            case "DentalEvaluationService":
                if (methodName.startsWith("update")) {
                    activity = "Evaluación dental editada";
                save = true;
                }
                break;
            case "DentalPredictionService":
                if (methodName.startsWith("update")) {
                    activity = "Pronóstico por pieza editado";
                save = true;
                }
                break;
            case "GalleryService":
                if (methodName.startsWith("upload")) {
                    String imageName = "";
                    for (Object arg : joinPoint.getArgs()) {
                        if (arg instanceof String) {
                            imageName = (String) arg;
                            break;
                        }
                    }
                    activity = "Se cargó una nueva imágen para " + imageName.replace("-", " ");
                save = true;
                }
                break;
            case "HealthQuestionnaireService":
                if (methodName.startsWith("update")) {
                    activity = "Cuestionario de salud editado";
                save = true;
                }
                break;
            case "LabsService":
                if (methodName.startsWith("upload")) {
                    String labName = "";
                    for (Object arg : joinPoint.getArgs()) {
                        if (arg instanceof String) {
                            labName = (String) arg;
                            break;
                        }
                    }
                    activity = "Se cargó un nuevo archivo para " + labName;
                    save = true;
                } else if (methodName.startsWith("update")) {
                    activity = "Notas de laboratorios editadas";
                save = true;
                }
                break;
            case "MedicalHistoryService":
                if (methodName.startsWith("update")) {
                    activity = "Historia clínica editada";
                save = true;
                }
                break;
            case "PatientService":
                if (methodName.startsWith("update")) {
                    activity = "Datos del paciente editados";
                    save = true;
                } else if (methodName.startsWith("create")) {
                    String patientName = "";
                    for (Object arg : joinPoint.getArgs()) {
                        if (arg instanceof Patient) {
                            patientId = ((Patient) arg).getId();
                            patientName = ((Patient) arg).getName() + " " + ((Patient) arg).getSurname();;
                            patient = (Patient) arg;
                            break;
                        }
                    }
                    activity = "Alta de paciente " + patientName;
                save = true;
                }
                break;
            case "WorkPlanService":
                if (methodName.startsWith("add")) {
                    activity = "Nueva etapa agregada al plan de trabajo actual";
                    save = true;
                } else if (methodName.startsWith("create")) {
                    activity = "Nuevo plan de trabajo creado";
                    save = true;
                } else if (methodName.startsWith("close")) {
                    activity = "Plan de trabajo terminado";
                    save = true;
                }
                break;
            case "SurgicalProtocolService":
                if (methodName.startsWith("create")) {
                    activity = "Nuevo protocolo quirúrgico creado";
                    save = true;
                }
                break;
            default:
                System.out.println("servicio incorrecto " + joinPoint.getTarget().getClass().getSimpleName());
                break;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication != null ? authentication.getName() : "Anonymous";
        String userRealName = userService.getUserRealName(username);
        if (save) {
            ActivityLog activityLog = new ActivityLog();
            activityLog.setActivity(activity);
            activityLog.setTimestamp(LocalDateTime.now());
            activityLog.setUsername(userRealName);
            activityLog.setPatient(patient);
            activityLogRepository.save(activityLog);
        }
    }
}
