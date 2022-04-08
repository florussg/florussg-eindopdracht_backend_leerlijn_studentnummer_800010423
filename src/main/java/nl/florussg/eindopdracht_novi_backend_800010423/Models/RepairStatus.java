package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum RepairStatus {

        PENDING_APPROVAL,
        NOT_APPROVED,
        APPROVED,
        STARTED,
        PASS,
        FAIL,
        CANCELLED

    }


