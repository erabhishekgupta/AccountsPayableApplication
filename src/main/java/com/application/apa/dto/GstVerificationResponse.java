package com.application.apa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GstVerificationResponse {

    @JsonProperty("taxpayerInfo")
    private TaxpayerInfo taxpayerInfo;

    @JsonProperty("error")
    private boolean error;

    @JsonProperty("message")
    private String message;

    public TaxpayerInfo getTaxpayerInfo() {
        return taxpayerInfo;
    }

    public void setTaxpayerInfo(TaxpayerInfo taxpayerInfo) {
        this.taxpayerInfo = taxpayerInfo;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

  
    public boolean isValid() {
        return !error &&
               taxpayerInfo != null &&
               "Active".equalsIgnoreCase(taxpayerInfo.getSts());
    }

  
    public String getPan() {
        return taxpayerInfo != null ? taxpayerInfo.getPanNo() : null;
    }

    
    public static class TaxpayerInfo {
        @JsonProperty("gstin")
        private String gstin;

        @JsonProperty("lgnm")
        private String legalName;

        @JsonProperty("sts")
        private String sts;

        @JsonProperty("panNo")
        private String panNo;

        // Add other fields if needed

        public String getGstin() {
            return gstin;
        }

        public void setGstin(String gstin) {
            this.gstin = gstin;
        }

        public String getLegalName() {
            return legalName;
        }

        public void setLegalName(String legalName) {
            this.legalName = legalName;
        }

        public String getSts() {
            return sts;
        }

        public void setSts(String sts) {
            this.sts = sts;
        }

        public String getPanNo() {
            return panNo;
        }

        public void setPanNo(String panNo) {
            this.panNo = panNo;
        }
    }
}
