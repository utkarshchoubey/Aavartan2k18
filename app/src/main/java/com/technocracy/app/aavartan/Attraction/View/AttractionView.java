package com.technocracy.app.aavartan.Attraction.View;

import com.technocracy.app.aavartan.Attraction.Model.Data.Attraction;

import java.util.List;

public interface AttractionView {
    void showProgressBar(boolean b);

    void showAttractionsFromDatabase();

    void showMessage(String string);

    void showAttractions(List<Attraction> attractionList);
}
