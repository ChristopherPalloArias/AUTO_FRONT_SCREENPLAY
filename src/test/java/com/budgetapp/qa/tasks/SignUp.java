package com.budgetapp.qa.tasks;

import com.budgetapp.qa.ui.DashboardUI;
import com.budgetapp.qa.ui.LoginUI;
import com.budgetapp.qa.ui.SignUpUI;
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

public class SignUp implements Task {

    private static final String DEFAULT_PASSWORD = "Password123!";

    private final String displayName;
    private final String emailBase;

    public SignUp(String displayName, String emailBase) {
        this.displayName = displayName;
        this.emailBase = emailBase;
    }

    public static SignUp withNameAndEmail(String displayName, String emailBase) {
        return Tasks.instrumented(SignUp.class, displayName, emailBase);
    }

    @Override
    @Step("{0} registers a new account with name '#displayName' and then logs in")
    public <T extends Actor> void performAs(T actor) {
        String baseUrl = SystemEnvironmentVariables.createEnvironmentVariables()
                .getProperty("webdriver.base.url");
        String uniqueEmail = emailBase.replace("@", "+" + System.currentTimeMillis() + "@");

        actor.attemptsTo(
                Open.url(baseUrl + "/register"),
                WaitUntil.the(SignUpUI.DISPLAY_NAME_INPUT, WebElementStateMatchers.isVisible()),
                Enter.theValue(displayName).into(SignUpUI.DISPLAY_NAME_INPUT),
                Enter.theValue(uniqueEmail).into(SignUpUI.EMAIL_INPUT),
                Enter.theValue(DEFAULT_PASSWORD).into(SignUpUI.PASSWORD_INPUT),
                Enter.theValue(DEFAULT_PASSWORD).into(SignUpUI.CONFIRM_PASSWORD_INPUT),
                Click.on(SignUpUI.SUBMIT_BUTTON),
                WaitUntil.the(LoginUI.EMAIL_INPUT, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(30).seconds(),
                Enter.theValue(uniqueEmail).into(LoginUI.EMAIL_INPUT),
                Enter.theValue(DEFAULT_PASSWORD).into(LoginUI.PASSWORD_INPUT),
                Click.on(LoginUI.SUBMIT_BUTTON),
                WaitUntil.the(DashboardUI.REPORTS_HEADING, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(30).seconds()
        );
    }
}
