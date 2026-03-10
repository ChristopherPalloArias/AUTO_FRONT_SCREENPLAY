package com.budgetapp.qa.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginUI {

    public static final Target EMAIL_INPUT =
            Target.the("email input field").located(By.id("email"));

    public static final Target PASSWORD_INPUT =
            Target.the("password input field").located(By.id("password"));

    public static final Target SUBMIT_BUTTON =
            Target.the("login submit button").located(By.cssSelector("button[type='submit']"));

    public static final Target SUCCESS_TOAST =
            Target.the("login success toast").located(By.cssSelector("[data-sonner-toast][data-type='success']"));
}
