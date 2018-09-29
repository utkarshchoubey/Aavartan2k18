package com.technocracy.app.aavartan.Sponsors.Model.Data;

import java.util.List;

public class SponsData {
    private boolean success;
    private String message;
    private List<Sponsor> sponsorList;
    public SponsData(boolean success, String message, List<Sponsor> sponsorList) {
        this.success = success;
        this.message = message;
        this.sponsorList = sponsorList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Sponsor> getSponsorList() {
        return sponsorList;
    }
}