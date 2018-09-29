package com.technocracy.app.aavartan.Event;

import com.technocracy.app.aavartan.Event.Model.Data.RegisterData;

/**
 * Created by Abhi on 26-Sep-17.
 */

public interface RegisterEventCallback {
    void onSuccess(RegisterData body);

    void onFailure();
}
