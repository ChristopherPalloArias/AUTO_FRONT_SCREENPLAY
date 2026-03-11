package com.budgetapp.qa.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.annotations.Step;

public class ClearFieldViaJavaScript implements Interaction {

    private final Target target;

    public ClearFieldViaJavaScript(Target target) {
        this.target = target;
    }

    public static ClearFieldViaJavaScript on(Target target) {
        return new ClearFieldViaJavaScript(target);
    }

    @Override
    @Step("{0} clears the field #target via JavaScript")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).evaluateJavascript(
                "arguments[0].value='';",
                target.resolveFor(actor)
        );
    }
}
