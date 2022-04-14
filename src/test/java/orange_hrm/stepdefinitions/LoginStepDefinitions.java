package orange_hrm.stepdefinitions;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import tasks.ClickOn;
import tasks.InputText;
import tasks.NavigateTo;

public class LoginStepDefinitions {
    @Given("{actor} open Orange HRM login")
    public void loadPage(Actor actor) throws Exception {
        actor.wasAbleTo(NavigateTo.theUrl("HRM Login"));
    }

    @When("{actor} input username and password")
    public void InputCredential(Actor actor) throws Exception {
        Dotenv dotenv = Dotenv.load();

        String username = dotenv.get("USERNAME_HRM");
        String password = dotenv.get("PASSWORD_HRM");

        actor.attemptsTo(
                InputText.onField("HRM username", username),
                InputText.onField("HRM password", password)
        );
    }

    @And("{actor} click login button")
    public void clickLoginButton(Actor actor) throws Exception {
        actor.attemptsTo(
                ClickOn.button("HRM Log In")
        );
    }

    @Then("{actor} cam login successfully")
    public void validateLoginSuccess(Actor actor) {
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().contains("/index.php/dashboard")
        );
    }
}
