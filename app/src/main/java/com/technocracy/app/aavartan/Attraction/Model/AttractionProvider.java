package com.technocracy.app.aavartan.Attraction.Model;

import com.technocracy.app.aavartan.Attraction.AttractionCallback;

public interface AttractionProvider {
    void getAttractions(AttractionCallback callback);
}
