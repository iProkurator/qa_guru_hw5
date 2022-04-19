package guru.qa.tests.temp;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {

        String firstName = "Pavel";
        String lastName = "Pilatov";
        String email = lastName.toLowerCase() + "@gmail.com";
        String mobile = "9265001234";
        String address = "Russia, Khimki";

        open("/automation-practice-form");
//        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
//        executeJavaScript("$('footer').remove()");
//        executeJavaScript("$('#fixedban'.remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1982");
        $(".react-datepicker__day--002:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Co").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("mypic1.jpg");
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Student Name " + firstName + " " + lastName),
                text("Mobile " + mobile),
                text("Picture mypic1.jpg"),
                text("Student Email " + email),
                text("Gender Male"),
                text("Date of Birth 02 May,1982"),
                text("Subjects Computer Science"),
                text("Hobbies Sports"),
                text("Address " + address),
                text("State and City NCR Delhi"));

    }

}
