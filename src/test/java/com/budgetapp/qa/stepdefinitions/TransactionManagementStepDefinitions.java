package com.budgetapp.qa.stepdefinitions;

import com.budgetapp.qa.interactions.Pause;
import com.budgetapp.qa.questions.FinancialReport;
import com.budgetapp.qa.tasks.MapsTo;
import com.budgetapp.qa.tasks.RegisterIncome;
import com.budgetapp.qa.tasks.SignUp;
import com.budgetapp.qa.ui.DashboardUI;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.text.NumberFormat;
import java.util.Locale;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;

public class TransactionManagementStepDefinitions {

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que el visitante accede a la página de registro de la plataforma")
    public void theVisitorAccessesTheRegistrationPage() {
        theActorCalled("Visitor");
    }

    @Cuando("él crea una cuenta nueva con el nombre {string} y el correo base {string}")
    public void heCreatesANewAccountWithNameAndEmail(String name, String emailBase) {
        theActorInTheSpotlight().wasAbleTo(
                SignUp.withNameAndEmail(name, emailBase),
                Pause.forSeconds(1)
        );
    }

    @Y("registra un nuevo ingreso de tipo {string} por un monto de {int}")
    public void heRegistersANewIncomeEntry(String description, int amount) {
        theActorInTheSpotlight().attemptsTo(
                MapsTo.theTransactionsList(),
                RegisterIncome.with(description, String.valueOf(amount)),
                Pause.forSeconds(2)
        );
    }

    @Entonces("el reporte financiero del panel principal debe reflejar el balance exacto de {int}")
    public void theFinancialReportShouldReflectTheExactBalance(int expectedAmount) {
        theActorInTheSpotlight().attemptsTo(
                MapsTo.theDashboard(),
                WaitUntil.the(DashboardUI.GENERATE_REPORTS_BUTTON, WebElementStateMatchers.isVisible()),
                Click.on(DashboardUI.GENERATE_REPORTS_BUTTON),
                WaitUntil.the(DashboardUI.BALANCE_VALUE, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(15).seconds()
        );

        NumberFormat formatter = NumberFormat.getInstance(new Locale("es", "CO"));
        String formattedAmount = formatter.format(expectedAmount);

        theActorInTheSpotlight().should(
                seeThat(FinancialReport.balanceValue(), containsString(formattedAmount))
        );

        theActorInTheSpotlight().attemptsTo(
                Pause.forSeconds(1)
        );
    }
}
