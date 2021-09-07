package com.relational.dataaccessjdbc.model;

public class Ciudad {

    private long ciudadId;
    private String description;
    private int paisId;

    public Ciudad(long ciudadId, String description, int paisId) {
        this.ciudadId = ciudadId;
        this.description = description;
        this.paisId = paisId;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "ciudadId=" + ciudadId +
                ", description='" + description + '\'' +
                ", paisId=" + paisId +
                '}';
    }

    public long getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(long ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPaisId() {
        return paisId;
    }

    public void setPaisId(int paisId) {
        this.paisId = paisId;
    }
}
