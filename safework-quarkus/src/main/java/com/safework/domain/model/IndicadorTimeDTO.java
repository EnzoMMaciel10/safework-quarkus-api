package com.safework.domain.model;

public class IndicadorTimeDTO {

    private Long timeId;
    private Double mediaHumor;
    private Double mediaSono;
    private Double mediaCarga;
    private Long totalCheckins;

    public IndicadorTimeDTO() {
    }

    public IndicadorTimeDTO(Long timeId, Double mediaHumor, Double mediaSono, Double mediaCarga, Long totalCheckins) {
        this.timeId = timeId;
        this.mediaHumor = mediaHumor;
        this.mediaSono = mediaSono;
        this.mediaCarga = mediaCarga;
        this.totalCheckins = totalCheckins;
    }

    public Long getTimeId() {
        return timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public Double getMediaHumor() {
        return mediaHumor;
    }

    public void setMediaHumor(Double mediaHumor) {
        this.mediaHumor = mediaHumor;
    }

    public Double getMediaSono() {
        return mediaSono;
    }

    public void setMediaSono(Double mediaSono) {
        this.mediaSono = mediaSono;
    }

    public Double getMediaCarga() {
        return mediaCarga;
    }

    public void setMediaCarga(Double mediaCarga) {
        this.mediaCarga = mediaCarga;
    }

    public Long getTotalCheckins() {
        return totalCheckins;
    }

    public void setTotalCheckins(Long totalCheckins) {
        this.totalCheckins = totalCheckins;
    }
}
