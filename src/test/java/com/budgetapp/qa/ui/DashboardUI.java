package com.budgetapp.qa.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DashboardUI {

    public static final Target REPORTS_HEADING =
            Target.the("financial reports heading").located(By.xpath("//h2[contains(text(),'Reportes Financieros')]"));

    public static final Target TOTAL_INCOME_VALUE =
            Target.the("total income value").located(By.xpath("//p[normalize-space()='Ingresos del período']/preceding-sibling::div[contains(@class,'text-green')]"));

    public static final Target TRANSACTIONS_MENU_LINK =
            Target.the("transactions menu link").located(By.xpath("//a[contains(@href, '/transactions')]"));

    public static final Target DASHBOARD_MENU_LINK =
            Target.the("dashboard menu link").located(By.xpath("//a[contains(@href, '/dashboard')]"));
}
