package com.eightfold.transformer.config;

import java.util.List;

public class Config {

    private List<FieldMapping> fields;

    private boolean include_confidence;

    private boolean include_provenance;

    private String on_missing;

    public Config() {
    }

    public List<FieldMapping> getFields() {
        return fields;
    }

    public void setFields(List<FieldMapping> fields) {
        this.fields = fields;
    }

    public boolean isInclude_confidence() {
        return include_confidence;
    }

    public void setInclude_confidence(boolean include_confidence) {
        this.include_confidence = include_confidence;
    }

    public boolean isInclude_provenance() {
        return include_provenance;
    }

    public void setInclude_provenance(boolean include_provenance) {
        this.include_provenance = include_provenance;
    }

    public String getOn_missing() {
        return on_missing;
    }

    public void setOn_missing(String on_missing) {
        this.on_missing = on_missing;
    }
}