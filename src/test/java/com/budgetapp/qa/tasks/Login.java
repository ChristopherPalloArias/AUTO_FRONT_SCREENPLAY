package com.budgetapp.qa.tasks;

import com.budgetapp.qa.ui.LoginUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import net.thucydides.model.environment.SystemEnvironmentVariables;

public class Login implements Task {

    private final String email;
    private final String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Login withCredentials(String email, String password) {
        return Tasks.instrumented(Login.class, email, password);
    }

    @Override
    @Step("{0} logs in with email '#email'")
    public <T extends Actor> void performAs(T actor) {
        String baseUrl = SystemEnvironmentVariables.createEnvironmentVariables()
                .getProperty("webdriver.base.url");

        actor.attemptsTo(
                Open.url(baseUrl),
                WaitUntil.the(LoginUI.EMAIL_INPUT, WebElementStateMatchers.isVisible()),
                Enter.theValue(email).into(LoginUI.EMAIL_INPUT),
                Enter.theValue(password).into(LoginUI.PASSWORD_INPUT),
                Click.on(LoginUI.SUBMIT_BUTTON)
        );
    }
}
