package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import models.RequestModel.JobRequest;
import models.RequestModel.DataUserRequest;
import models.ResponseModel.*;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ApiSteps {
    @Step("Получение юзера")
    public UserResponse getUserById(Integer id, Integer status){
        return given()
                .when()
                .get("api/users/"+ id)
                .then()
                .statusCode(status)
                .extract().response().body().as(UserResponse.class);
    }

    @Step("Проверка юзера")
    public void checkUser(UserResponse actualResponse, UserResponse expectedResponse){
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Step("Проверка создания юзера")
    public void createUser(String name, String job){
        given()
                .body(new JobRequest(name, job))
                .when()
                .post("api/users")
                .then()
                .statusCode(201).log().all()
                .extract().response().body().as(JobResponse.class);
    }

    @Step("Обновление информации о юзере")
    public void updateUser(Integer id, String name, String job, Integer status){
        given()
                .body(new JobRequest(name, job))
                .put("api/users/" + id)
                .then().log().all().statusCode(status)
                .extract().response().body().as(JobUpdateResponse.class);
    }

    @Step("Удаление юзера")
    public void deleteUser(Integer id, Integer status){
        given()
                .when()
                .delete("/api/users/"+id)
                .then().statusCode(status).log().all();
    }

    @Step("Проверка валидной регистрации")
    public void checkRegSucc(String email, String password,Integer status){
        RegResponse actualResponse = given()
                .body(new DataUserRequest(email, password))
                .when()
                .post("api/register")
                .then().log().all().statusCode(status)
                .extract().response().body().as(RegResponse.class);
        RegResponse expectedResponse = new RegResponse(4,"QpwL5tke4Pnpja7X4");
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Step("Проверка невалидной регистрации")
    public void checkRegUnSucc(String email, String password,Integer status){
        ErrorResponse actualResponse = given()
                .body(new DataUserRequest(email, password))
                .when()
                .post("api/register")
                .then().log().all().statusCode(status)
                .extract().response().body().as(ErrorResponse.class);
        ErrorResponse expectedResponse = new ErrorResponse("Missing password");
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Step("Проверка валидной авторизации")
    public void checkLogSucc(String email, String password,Integer status){
        LogResponse actualResponse = given()
                .body(new DataUserRequest(email, password))
                .when()
                .post("api/login")
                .then().log().all().statusCode(status)
                .extract().response().body().as(LogResponse.class);
        LogResponse expectedResponse = new LogResponse("QpwL5tke4Pnpja7X4");
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Step("Проверка невалидной авторизации")
    public void checkLogUnSucc(String email, String password,Integer status){
        ErrorResponse actualResponse = given()
                .body(new DataUserRequest(email, password))
                .when()
                .post("api/login")
                .then().log().all().statusCode(status)
                .extract().response().body().as(ErrorResponse.class);
        ErrorResponse expectedResponse = new ErrorResponse("Missing password");
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Step("Получение списка юзеров и его проверка")
    public void getUserList(String page, Integer numberPage){
        List<DataUserResponse> users =  given()
                .when()
                .get("api/users?"+page+"="+numberPage)
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", DataUserResponse.class);
        Assert.assertTrue(users.stream().allMatch(x->x.email.endsWith("reqres.in")));
        Assert.assertTrue(users.stream().allMatch(x->x.avatar.contains(x.getId().toString())));
        List<String> avatars = users.stream().map(DataUserResponse::getAvatar).collect(Collectors.toList());
        List<Integer> ids = users.stream().map(DataUserResponse::getId).collect(Collectors.toList());
        for(int i = 0; i<avatars.size();i++){
            System.out.println(avatars.get(i));
            System.out.println(ids.get(i));
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }
    }

    @Step("Получение списка цветов")
    public void getColorsList(){
        List<DataColorResponse> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", DataColorResponse.class);
        List<Integer> years = colors.stream().map(DataColorResponse::getYear).collect(Collectors.toList());
        List<Integer> yearsSorted = years.stream().sorted().collect(Collectors.toList());
        System.out.println(years);
        System.out.println(yearsSorted);
        for(int i=0;i< years.size();i++){
            Assert.assertEquals(years,yearsSorted);
        }
    }

    @Step("Получение цвета")
    public ColorsResponse getColorById(Integer id, Integer status){
        return given()
                .when()
                .get("api/unknown/"+id)
                .then().log().all()
                .extract().response().body().as(ColorsResponse.class);
    }

    @Step("Проверка цвета")
    public void checkColor(ColorsResponse actualResponse, ColorsResponse expectedResponse) {
        Assert.assertEquals(actualResponse, expectedResponse);
    }
}
