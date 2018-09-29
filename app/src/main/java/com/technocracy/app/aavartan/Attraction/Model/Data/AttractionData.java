package com.technocracy.app.aavartan.Attraction.Model.Data;


import java.util.List;

public class AttractionData {
    private boolean success;
    private List<Attraction> attractions;

    public AttractionData(boolean success, List<Attraction> attractions) {
        this.success = success;
        this.attractions = attractions;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Attraction> getAttractionList() {
        return attractions;
    }
}
