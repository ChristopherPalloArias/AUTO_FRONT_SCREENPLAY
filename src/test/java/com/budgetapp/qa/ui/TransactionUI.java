package com.budgetapp.qa.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class TransactionUI {

    public static final Target NEW_TRANSACTION_BUTTON =
            Target.the("new transaction button").located(By.xpath("//button[.//span[text()='Nueva Transacción']]"));

    public static final Target TRANSACTION_MODAL_TITLE =
            Target.the("transaction modal title").located(By.xpath("//h2[text()='Nueva Transacción']"));

    public static final Target TYPE_SELECT_TRIGGER =
            Target.the("transaction type select trigger").located(By.xpath("(//div[@role='dialog']//button[@role='combobox'])[1]"));

    public static final Target INCOME_OPTION =
            Target.the("income type option").located(By.xpath("//div[@role='option'][normalize-space()='Ingreso']"));

    public static final Target CATEGORY_SELECT_TRIGGER =
            Target.the("transaction category select trigger").located(By.xpath("(//div[@role='dialog']//button[@role='combobox'])[2]"));

    public static final Target SALARY_CATEGORY_OPTION =
            Target.the("salary category option").located(By.xpath("//div[@role='option'][normalize-space()='Salario']"));

    public static final Target DESCRIPTION_INPUT =
            Target.the("transaction description input").located(By.cssSelector("input[placeholder='Ej: Supermercado']"));

    public static final Target AMOUNT_INPUT =
            Target.the("transaction amount input").located(By.cssSelector("input[type='number'][placeholder='0.00']"));

    public static final Target CREATE_TRANSACTION_BUTTON =
            Target.the("create transaction submit button").located(By.xpath("//button[@type='submit'][contains(.,'Crear Transacción')]"));
}
