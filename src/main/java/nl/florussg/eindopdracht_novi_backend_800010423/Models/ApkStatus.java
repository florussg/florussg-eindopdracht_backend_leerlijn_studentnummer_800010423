package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ApkStatus {

//        PASS,
//        STARTED,
//        FAIL,
//        CANCELLED
        @JsonProperty("started")
        APK_inspection_started,
        @JsonProperty("pass")
        APK_pass,
        @JsonProperty("fail")
        APK_fail,
        @JsonProperty("cancelled")
        APK_inspection_cancelled;
    }

