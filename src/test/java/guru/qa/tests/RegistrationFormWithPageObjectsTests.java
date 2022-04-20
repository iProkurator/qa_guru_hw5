package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import guru.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

public class RegistrationFormWithPageObjectsTests {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    Faker faker = new Faker();

    DateFormat dateFormat = new SimpleDateFormat("dd MMMM,yyyy");

    Date birthDate = faker.date().birthday();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            gender = faker.demographic().sex(),
            email = faker.internet().emailAddress(),
            mobile = faker.phoneNumber().subscriberNumber(10),
            address = faker.address().fullAddress(),
            subjects = "Computer Science",
            hobbies = "Sports",
            pictureName = "mypic1.jpg",
            state = "NCR",
            city = "Delhi",

            expectedFullName = format("%s %s", firstName, lastName),
            expectedBirthDay = dateFormat.format(birthDate),
            expectedStateAndCity = state + " " + city;

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
                .setBirthDate(birthDate)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setUploadPicture(pictureName)
                .setAddress(address)
                .setCityAndState(state, city)

                .submitForm()

                .checkResultHeader("Thanks for submitting the form")

                .checkResult("Student Name", expectedFullName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", expectedBirthDay)
                .checkResult("Student Email", email)
                .checkResult("Address", address)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", pictureName)
                .checkResult("State and City", expectedStateAndCity);
    }

}
