package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum RepairStatus {

        PENDINGAPPROVAL,
        NOTAPPROVED,
        STARTED,
        PASS,
        FAIL,
        CANCELLED


//        REPAIR_pending_on_approval_customer,
//        REPAIR_not_approved_by_customer,
//
//        @JsonProperty("started")
//        REPAIR_started,
//        REPAIR_pass,
//        REPAIR_fail,
//        REPAIR_cancelled
    }


