package com.budgetapp.qa.questions;

import com.budgetapp.qa.ui.DashboardUI;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class FinancialReport {

    public static Question<Boolean> isVisible() {
        return actor -> {
            actor.attemptsTo(
                    WaitUntil.the(DashboardUI.REPORTS_HEADING, WebElementStateMatchers.isVisible())
            );
            return DashboardUI.REPORTS_HEADING.resolveFor(actor).isDisplayed();
        };
    }

    public static Question<String> totalIncomeValue() {
        return actor -> {
            actor.attemptsTo(
                    WaitUntil.the(DashboardUI.TOTAL_INCOME_VALUE, WebElementStateMatchers.isVisible())
            );
            return DashboardUI.TOTAL_INCOME_VALUE.resolveFor(actor).getText();
        };
    }
}
