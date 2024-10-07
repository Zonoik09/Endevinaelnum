package com.jonathan.endevinaelnum;

import java.io.Serializable;

public class Record implements Serializable {
    private String name;
    private int attempts;

    public Record(String name, int attempts) {
        this.name = name;
        this.attempts = attempts;
    }

    public String getName() {
        return name;
    }

    public int getAttempts() {
        return attempts;
    }
}
