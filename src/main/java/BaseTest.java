import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.*;
import steps.Steps;

import static io.restassured.RestAssured.given;

public class BaseTest implements Steps {
    @BeforeMethod(description = "Настройки запроса")
    public void configureRestAssured() {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.requestSpecification = given()
                .contentType(ContentType.JSON);
    }
    @BeforeSuite(description = "Начало тестирования")
    public void befS(){
        System.out.println("Начало тестирования");
    }
    @AfterSuite(description = "Тест завершен")
    public void aftS(){
        System.out.println("Тест завершен");
    }
}
