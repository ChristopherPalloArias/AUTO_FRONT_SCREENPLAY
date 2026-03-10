package com.budgetapp.qa.stepdefinitions;

import com.budgetapp.qa.questions.FinancialReport;
import com.budgetapp.qa.tasks.Login;
import com.budgetapp.qa.tasks.MapsTo;
import com.budgetapp.qa.tasks.RegisterIncome;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class TransactionManagementStepDefinitions {

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que Christopher se autentica exitosamente en la plataforma financiera")
    public void christopherAuthenticatesSuccessfully() {
        theActorCalled("Christopher").attemptsTo(
                Login.withCredentials("tester@test.com", "Password123!")
        );
        theActorInTheSpotlight().should(
                seeThat(FinancialReport.isVisible(), is(true))
        );
    }

    @Cuando("el registra un nuevo ingreso de tipo {string} por un monto de {int}")
    public void heRegistersANewIncomeEntry(String description, int amount) {
        theActorInTheSpotlight().attemptsTo(
                MapsTo.theTransactionsList(),
                RegisterIncome.with(description, String.valueOf(amount))
        );
    }

    @Entonces("el reporte financiero del panel principal debe reflejar el ingreso registrado")
    public void theFinancialReportShouldReflectTheRegisteredIncome() {
        theActorInTheSpotlight().attemptsTo(
                MapsTo.theDashboard()
        );
        theActorInTheSpotlight().should(
                seeThat(FinancialReport.isVisible(), is(true)),
                seeThat(FinancialReport.totalIncomeValue(), not(emptyOrNullString()))
        );
    }
}
