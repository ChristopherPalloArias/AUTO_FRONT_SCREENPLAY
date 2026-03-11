package com.budgetapp.qa.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SignUpUI {

    public static final Target DISPLAY_NAME_INPUT =
            Target.the("display name input field").located(By.id("displayName"));

    public static final Target EMAIL_INPUT =
            Target.the("email input field").located(By.id("email"));

    public static final Target PASSWORD_INPUT =
            Target.the("password input field").located(By.id("password"));

    public static final Target CONFIRM_PASSWORD_INPUT =
            Target.the("confirm password input field").located(By.id("confirmPassword"));

    public static final Target SUBMIT_BUTTON =
            Target.the("register submit button").located(By.xpath("//button[@type='submit'][contains(.,'Crear Cuenta')]"));

    public static final Target REGISTER_LINK =
            Target.the("register link").located(By.xpath("//a[contains(.,'Regístrate aquí')]"));
}
