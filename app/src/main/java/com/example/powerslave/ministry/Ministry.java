package com.example.powerslave.ministry;

import com.example.powerslave.person.Minister;

public abstract class Ministry {
    private final String name;
    private Minister minister;

    protected Ministry(String name, Minister minister) {
        this.name = name;
        this.minister = minister;
    }

    public String getName() {
        return name;
    }

    public Minister getMinister() {
        return minister;
    }

    public void setMinister(Minister minister) {
        this.minister = minister;
    }
}
