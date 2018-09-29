package com.technocracy.app.aavartan.Event.Presenter;

import android.content.Context;

import com.technocracy.app.aavartan.Event.EventCallback;
import com.technocracy.app.aavartan.Event.Model.Data.EventData;
import com.technocracy.app.aavartan.Event.Model.Data.RegisterData;
import com.technocracy.app.aavartan.Event.Model.EventProvider;
import com.technocracy.app.aavartan.Event.RegisterEventCallback;
import com.technocracy.app.aavartan.Event.View.EventView;
import com.technocracy.app.aavartan.Event.View.RegisterEventView;
import com.technocracy.app.aavartan.R;

public class EventPresenterImpl implements EventPresenter {

    private EventView view;
    private EventProvider provider;
    private Context context;
    private RegisterEventView view2;

    public EventPresenterImpl(EventView view, EventProvider provider, Context context) {
        this.view = view;
        this.provider = provider;
        this.context = context;
    }

    public EventPresenterImpl(RegisterEventView view, EventProvider provider, Context context) {
        this.view2 = view;
        this.provider = provider;
        this.context = context;
    }

    @Override
    public void getEvents(String eventSetId) {
        view.showProgressBar(true);
        if (eventSetId.equals("fun")) {
            provider.getFunEvent(new EventCallback() {
                @Override
                public void onFailure() {
                    view.showProgressBar(false);
                    view.showEventsFromDatabase();
                    view.showMessage(context.getResources().getString(R.string.Connection_Error));

                }

                @Override
                public void onSuccess(EventData body) {
                    view.showProgressBar(false);
                    if (body.isSuccess()) {
                        view.showEvents(body.getEventList());
                    } else {
                        view.showEventsFromDatabase();
                        view.showMessage(body.getMessage());
                    }
                }
            });
        } else if (eventSetId.equals("manager")) {
            provider.getManagerialEvent(new EventCallback() {
                @Override
                public void onFailure() {
                    view.showProgressBar(false);
                    view.showEventsFromDatabase();
                    view.showMessage(context.getResources().getString(R.string.Connection_Error));

                }

                @Override
                public void onSuccess(EventData body) {
                    view.showProgressBar(false);
                    if (body.isSuccess()) {
                        view.showEvents(body.getEventList());
                    } else {
                        view.showEventsFromDatabase();
                        view.showMessage(body.getMessage());
                    }
                }
            });
        } else if (eventSetId.equals("tech")) {
            provider.getTechEvent(new EventCallback() {
                @Override
                public void onFailure() {
                    view.showProgressBar(false);
                    view.showEventsFromDatabase();
                    view.showMessage(context.getResources().getString(R.string.Connection_Error));

                }

                @Override
                public void onSuccess(EventData body) {
                    view.showProgressBar(false);
                    if (body.isSuccess()) {
                        view.showEvents(body.getEventList());
                    } else {
                        view.showEventsFromDatabase();
                        view.showMessage(body.getMessage());
                    }
                }
            });
        } else {
            provider.getRoboEvent(new EventCallback() {
                @Override
                public void onFailure() {
                    view.showProgressBar(false);
                    view.showEventsFromDatabase();
                    view.showMessage(context.getResources().getString(R.string.Connection_Error));

                }

                @Override
                public void onSuccess(EventData body) {
                    view.showProgressBar(false);
                    if (body.isSuccess()) {
                        view.showEvents(body.getEventList());
                    } else {
                        view.showEventsFromDatabase();
                        view.showMessage(body.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void registerEvent(String userId, String eventId) {
        view2.showProgressBar(true);
        provider.registerEvent(userId, eventId, new RegisterEventCallback() {
            @Override
            public void onSuccess(RegisterData body) {
                view2.showProgressBar(false);
                if (body.isSuccess())
                    view2.showMessage(body.getMessage());
                else
                    view2.showMessage(body.getMessage());
            }

            @Override
            public void onFailure() {
                view2.showProgressBar(false);
                view2.showMessage(context.getResources().getString(R.string.Connection_Error));
            }
        });
    }
}