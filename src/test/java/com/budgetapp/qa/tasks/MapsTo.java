package com.budgetapp.qa.tasks;

import com.budgetapp.qa.ui.DashboardUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.annotations.Step;

public class MapsTo implements Task {

    public enum Section { DASHBOARD, TRANSACTIONS }

    private final Section destination;

    public MapsTo(Section destination) {
        this.destination = destination;
    }

    public static MapsTo theDashboard() {
        return new MapsTo(Section.DASHBOARD);
    }

    public static MapsTo theTransactionsList() {
        return new MapsTo(Section.TRANSACTIONS);
    }

    @Override
    @Step("{0} navigates to #destination")
    public <T extends Actor> void performAs(T actor) {
        if (destination == Section.TRANSACTIONS) {
            actor.attemptsTo(
                    Click.on(DashboardUI.TRANSACTIONS_MENU_LINK)
            );
        } else {
            actor.attemptsTo(
                    Click.on(DashboardUI.DASHBOARD_MENU_LINK),
                    WaitUntil.the(DashboardUI.REPORTS_HEADING, WebElementStateMatchers.isVisible())
            );
        }
    }
}
