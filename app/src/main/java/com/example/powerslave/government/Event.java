package com.example.powerslave.government;

import android.content.Context;
import android.content.res.Resources;

import com.example.powerslave.R;
import com.example.powerslave.ministry.Ministry;
import com.example.powerslave.person.Minister;
import com.example.powerslave.person.Sex;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class Event {

    private int eventId;
    private String eventTitle;
    private String preEventText;
    private String eventText;
    private Country country;
    private Random random = new Random();

    public Event(Context context, Country country) {
        this.country = country;
        this.eventId = this.random.nextInt(context.getResources().getStringArray(R.array.event_titles).length);
        this.eventTitle = context.getResources().getStringArray(R.array.event_titles)[eventId];
        this.preEventText = context.getResources().getStringArray(R.array.event_texts)[eventId];
        getEventEffect(eventId);
    }

    private void getEventEffect(int eventId) {
        switch (eventId) {
            case 0:
                Minister minister = country.getMinistryOfAgriculture().getMinister();
                Ministry ministry = country.getMinistryOfAgriculture();
                for (Ministry m : country.getMinistries().values()) {
                    if (random.nextInt(3) == 2) {
                        minister = m.getMinister();
                        ministry = m;
                    }
                }
                eventText = String.format(preEventText, minister.getName(), minister.getSurname(), ministry.getName());
                ministry.setMinister(country.vacant);
                break;
            case 1:
                minister = country.getMinistryOfAgriculture().getMinister();
                ministry = country.getMinistryOfAgriculture();
                for (Ministry m : country.getMinistries().values()) {
                    if (random.nextInt(3) == 2) {
                        minister = m.getMinister();
                        ministry = m;
                    }
                }
                eventText = String.format(preEventText, minister.getName(), minister.getSurname(), ministry.getName(), minister.getSex() == Sex.MALE ? "his" : "her");
                minister.setCompetency(minister.getCompetency() + 10);
                break;
            case 2:
                minister = country.getMinistryOfAgriculture().getMinister();
                ministry = country.getMinistryOfAgriculture();
                for (Ministry m : country.getMinistries().values()) {
                    if (random.nextInt(3) == 2) {
                        minister = m.getMinister();
                        ministry = m;
                    }
                }
                eventText = String.format(preEventText, minister.getName(), minister.getSurname(), ministry.getName(), minister.getSex() == Sex.MALE ? "his" : "her");
                minister.setCompetency(minister.getCompetency() - 10);
                break;
        }
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventText() {
        return eventText;
    }
}
