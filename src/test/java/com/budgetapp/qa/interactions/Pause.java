package com.budgetapp.qa.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.annotations.Step;

public class Pause implements Interaction {

    private final int seconds;

    public Pause(int seconds) {
        this.seconds = seconds;
    }

    public static Pause forSeconds(int seconds) {
        return new Pause(seconds);
    }

    @Override
    @Step("{0} pauses for #seconds second(s)")
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
