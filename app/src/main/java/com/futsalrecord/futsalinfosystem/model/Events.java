package com.futsalrecord.futsalinfosystem.model;

public class Events {
    private String eventName, entryFee, eventDetail, eventImage;

    public Events(String eventName, String entryFee, String eventDetail, String eventImage) {
        this.eventName = eventName;
        this.entryFee = entryFee;
        this.eventDetail = eventDetail;
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(String entryFee) {
        this.entryFee = entryFee;
    }

    public String getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
}
