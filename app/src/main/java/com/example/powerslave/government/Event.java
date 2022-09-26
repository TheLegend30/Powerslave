package com.example.powerslave.government;

import android.content.Context;
import android.content.res.Resources;

import com.example.powerslave.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class Event {

    private String eventText;
    private String[] eventChoices;
    private Method choiceEffect;
    private String choiceEffectText;
    //TODO: For effects use value effects (can get them by reflection)

    public Event(Context context) {
        Random random = new Random();
        int choicesArrId;
        Resources resources = context.getResources();

        int eventId = random.nextInt(context.getResources().getStringArray(R.array.event).length);

        eventText = resources.getStringArray(R.array.event)[eventId];

        eventId++;

        choicesArrId = resources.getIdentifier("event_choices_" + eventId, "array", context.getPackageName());
        eventChoices = resources.getStringArray(choicesArrId);

       // choiceEffect = this.getClass().getMethod(doEventEffect();)

    }

    public String getEventText() {
        return eventText;
    }

    public String[] getEventChoices() {
        return eventChoices;
    }

    public void doEventEffect(int id) {
        switch (id) {
            case 1:
                choiceEffectText = "+1 competence";
        }
    }
}
