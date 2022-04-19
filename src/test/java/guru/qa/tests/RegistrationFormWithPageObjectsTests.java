package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import guru.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class RegistrationFormWithPageObjectsTests {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            gender = faker.demographic().sex(),
            email = faker.internet().emailAddress(),
            mobile = faker.phoneNumber().subscriberNumber(10),
            address = faker.address().fullAddress();

    String expectedFullName = format("%s %s", firstName, lastName);

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {

        registrationFormPage.openPage()

                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobile)
                .setBirthDate("02", "May", "1982")
                .setSubjects("Computer")
                .setHobbies("Sports")
                .setUploadPicture("mypic1.jpg")
                .setAddress(address)
                .setCityAndState("NCR", "Delhi")

                .submit()

                .checkResultHeader("Thanks for submitting the form")

                .checkResult("Student Name", expectedFullName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Student Email", email)
                .checkResult("Address", address);
    }

}
