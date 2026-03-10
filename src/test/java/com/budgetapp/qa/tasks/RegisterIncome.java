package com.budgetapp.qa.tasks;

import com.budgetapp.qa.interactions.SelectFromRadix;
import com.budgetapp.qa.ui.TransactionUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.annotations.Step;

public class RegisterIncome implements Task {

    private final String description;
    private final String amount;

    public RegisterIncome(String description, String amount) {
        this.description = description;
        this.amount = amount;
    }

    public static RegisterIncome with(String description, String amount) {
        return new RegisterIncome(description, amount);
    }

    @Override
    @Step("{0} registers a new income: '#description' for amount '#amount'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(TransactionUI.NEW_TRANSACTION_BUTTON, WebElementStateMatchers.isVisible()),
                Click.on(TransactionUI.NEW_TRANSACTION_BUTTON),
                WaitUntil.the(TransactionUI.TRANSACTION_MODAL_TITLE, WebElementStateMatchers.isVisible()),
                SelectFromRadix.theOption(TransactionUI.INCOME_OPTION)
                        .fromTrigger(TransactionUI.TYPE_SELECT_TRIGGER),
                SelectFromRadix.theOption(TransactionUI.SALARY_CATEGORY_OPTION)
                        .fromTrigger(TransactionUI.CATEGORY_SELECT_TRIGGER),
                Enter.theValue(description).into(TransactionUI.DESCRIPTION_INPUT),
                Enter.theValue(amount).into(TransactionUI.AMOUNT_INPUT),
                Click.on(TransactionUI.CREATE_TRANSACTION_BUTTON)
        );
    }
}
