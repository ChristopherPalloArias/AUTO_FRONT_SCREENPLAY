package com.budgetapp.qa.tasks;

import com.budgetapp.qa.ui.LoginUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;

public class Login implements Task {

    private final String email;
    private final String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Login withCredentials(String email, String password) {
        return new Login(email, password);
    }

    @Override
    @Step("{0} logs in with email '#email'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("http://localhost:3000"),
                WaitUntil.the(LoginUI.EMAIL_INPUT, WebElementStateMatchers.isVisible()),
                Enter.theValue(email).into(LoginUI.EMAIL_INPUT),
                Enter.theValue(password).into(LoginUI.PASSWORD_INPUT),
                Click.on(LoginUI.SUBMIT_BUTTON)
        );
    }
}
