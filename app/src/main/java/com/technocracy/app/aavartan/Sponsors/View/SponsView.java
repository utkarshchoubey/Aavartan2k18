package com.technocracy.app.aavartan.Sponsors.View;

import com.technocracy.app.aavartan.Sponsors.Model.Data.Sponsor;

import java.util.List;

public interface SponsView {
    void showProgressBar(boolean b);

    void showSpons(List<Sponsor> sponsorList);

    void showMessage(String string);

}
