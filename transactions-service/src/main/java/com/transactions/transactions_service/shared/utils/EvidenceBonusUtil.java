package com.transactions.transactions_service.shared.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactions.transactions_service.contracts.domain.model.valueObjects.EvidenceType;
import org.springframework.stereotype.Component;

@Component
public class EvidenceBonusUtil {
    private final ObjectMapper objectMapper;

    public EvidenceBonusUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public int calculateBonus(
            EvidenceType evidenceType,
            String requirementsJson,
            String dataJson) throws JsonProcessingException {
        try {
            JsonNode requirements = objectMapper.readTree(requirementsJson);
            JsonNode data = objectMapper.readTree(dataJson);
            int bonus = 0;
            switch (evidenceType) {
                case CERTIFICATE -> bonus = calculateBonusByCertificateEvidence(requirements, data);
                //case PROMOTION -> bonus = calculateBonusByPromotionEvidence(requirements, data);
                case PUNCTUALITY -> bonus = calculateBonusByPunctualityEvidence(requirements, data);
                case SPECIALIZATION -> bonus=calculateBonusBySpecialityEvidence(requirements, data);
                case STUDY_WORKSHOP -> bonus=calculateBonusByStudyWorkshopEvidence(requirements, data);
                case JOB_TRAINING -> bonus=calculateBonusByJobTrainingEvidence(requirements, data);
                case PROACTIVITY -> bonus=calculateBonusByProactivityEvidence(requirements, data);
                default -> bonus=0;
            }
            return bonus;
        }catch (Exception e){
            return 0;
        }
    }

    private int calculateBonusByCertificateEvidence(JsonNode requirements, JsonNode data){
        JsonNode bonusRules = requirements.get("bonificacionesPorHoras");
        int hoursNumber = data.get("numeroDeHoras").asInt();

        for(JsonNode rule : bonusRules){
            int min = rule.get("min").asInt();
            int max = rule.get("max").asInt();
            if(hoursNumber>= min && hoursNumber<= max){
                return rule.get("recompensa").asInt();
            }
        }
        return 0;
    }

    private int calculateBonusByPunctualityEvidence(JsonNode requirements, JsonNode data) {
        JsonNode bonusRules = requirements.get("bonificacionesPorMesesPuntualidad");
        int months = data.get("mesesPuntualidad").asInt();

        for (JsonNode rule : bonusRules) {
            int min = rule.get("min").asInt();
            int max = rule.get("max").asInt();
            if (months >= min && months <= max) {
                return rule.get("recompensa").asInt();
            }
        }
        return 0;
    }

    private int calculateBonusBySpecialityEvidence(JsonNode requirements, JsonNode data) {
        JsonNode bonusRules = requirements.get("bonificacionesPorNivel");
        String level = data.get("nivel").asText();

        for (JsonNode rule : bonusRules) {
            String ruleLevel = rule.get("nivel").asText();
            if (level.equalsIgnoreCase(ruleLevel)) {
                return rule.get("recompensa").asInt();
            }
        }
        return 0;
    }

    private int calculateBonusByStudyWorkshopEvidence(JsonNode requirements, JsonNode data) {
        JsonNode bonusRules = requirements.get("bonificacionesPorHoras");
        int hours = data.get("horas").asInt();

        for (JsonNode rule : bonusRules) {
            int min = rule.get("min").asInt();
            int max = rule.get("max").asInt();
            if (hours >= min && hours <= max) {
                return rule.get("recompensa").asInt();
            }
        }
        return 0;
    }

    private int calculateBonusByJobTrainingEvidence(JsonNode requirements, JsonNode data) {
        JsonNode bonusRules = requirements.get("bonificacionesPorSesiones");
        int sessions = data.get("sesiones").asInt();

        for (JsonNode rule : bonusRules) {
            int min = rule.get("min").asInt();
            int max = rule.get("max").asInt();
            if (sessions >= min && sessions <= max) {
                return rule.get("recompensa").asInt();
            }
        }
        return 0;
    }

    private int calculateBonusByProactivityEvidence(JsonNode requirements, JsonNode data) {
        JsonNode bonusRules = requirements.get("bonificacionesPorImpacto");
        int impacto = data.get("impactoEstimado").asInt();

        for (JsonNode rule : bonusRules) {
            int min = rule.get("min").asInt();
            int max = rule.get("max").asInt();
            if (impacto >= min && impacto <= max) {
                return rule.get("recompensa").asInt();
            }
        }
        return 0;
    }
}
