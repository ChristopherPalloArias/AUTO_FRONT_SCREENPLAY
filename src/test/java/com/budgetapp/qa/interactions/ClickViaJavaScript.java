package com.budgetapp.qa.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.annotations.Step;

public class ClickViaJavaScript implements Interaction {

    private final Target target;

    public ClickViaJavaScript(Target target) {
        this.target = target;
    }

    public static ClickViaJavaScript on(Target target) {
        return new ClickViaJavaScript(target);
    }

    @Override
    @Step("{0} clicks via JavaScript on #target")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).evaluateJavascript(
                "arguments[0].click();",
                target.resolveFor(actor)
        );
    }
}
