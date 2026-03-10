package com.budgetapp.qa.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;

public class SelectFromRadix implements Interaction {

    private final Target trigger;
    private final Target option;

    public SelectFromRadix(Target trigger, Target option) {
        this.trigger = trigger;
        this.option = option;
    }

    public static SelectFromRadix theOption(Target option) {
        return new SelectFromRadix(null, option);
    }

    public SelectFromRadix fromTrigger(Target trigger) {
        return new SelectFromRadix(trigger, this.option);
    }

    @Override
    @Step("{0} selects a Radix option from #trigger")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(trigger, WebElementStateMatchers.isPresent())
        );
        BrowseTheWeb.as(actor).evaluateJavascript(
                "arguments[0].click();",
                trigger.resolveFor(actor)
        );
        actor.attemptsTo(
                WaitUntil.the(option, WebElementStateMatchers.isVisible()),
                Click.on(option)
        );
    }
}
