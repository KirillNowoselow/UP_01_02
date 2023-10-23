import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.ResponseModel.*;
import org.testng.annotations.Test;

@Epic("Тестовая группа 2")
@Feature("API тесты reqres")
public class ApiTest extends BaseTest {
    @Test(testName = "Проверка полученного юзера", description = "Проверка полученного юзера")
    public void getUserById(){
        UserResponse actualResponse = API_STEPS.getUserById(2,200);
        DataUserResponse dataResponse = new DataUserResponse(2,"janet.weaver@reqres.in","Janet","Weaver", "https://reqres.in/img/faces/2-image.jpg");
        SupportResponse supportResponse = new SupportResponse("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");
        UserResponse expectedResponse = new UserResponse(dataResponse, supportResponse);
        API_STEPS.checkUser(actualResponse, expectedResponse);
    }

    @Test(testName = "Проверка создания юзера", description = "Проверка создания юзера")
    public void createUserTest(){
        API_STEPS.createUser("morpheus", "leader");
    }

    @Test(testName = "Проверка обновления юзера", description = "Проверка обновления юзера")
    public void updateUser(){API_STEPS.updateUser(2, "morpheus","zion resident",200);}

    @Test(testName = "Проверка удаления юзера", description = "Проверка удаления юзера")
    public void deleteUser(){API_STEPS.deleteUser(2, 204);}

    @Test(testName = "Проверка валидной регистрации", description = "Проверка валидной регистрации")
    public void checkRegSucc(){API_STEPS.checkRegSucc("eve.holt@reqres.in","pistol",200);}

    @Test(testName = "Проверка невалидной регистрации", description = "Проверка невалидной регистрации")
    public void checkRegUnSucc(){API_STEPS.checkRegUnSucc("eve.holt@reqres.in","",400);}

    @Test(testName = "Проверка валидной авторизации", description = "Проверка валидной авторизации")
    public void checkLogSucc(){API_STEPS.checkLogSucc("eve.holt@reqres.in","cityslicka",200);}

    @Test(testName = "Проверка невалидной авторизации", description = "Проверка невалидной авторизации")
    public void checkLogUnSucc(){API_STEPS.checkLogUnSucc("peter@klaven","",400);}

    @Test(testName = "Проверка списка юзеров #1", description = "Проверка списка юзеров #1")
    public void getUserList1(){
        API_STEPS.getUserList("page", 2);
    }

    @Test(testName = "Проверка списка юзеров #2", description = "Проверка списка юзеров #2")
    public void getUserList2(){
        API_STEPS.getUserList("delay",3);
    }

    @Test(testName = "Запрос несуществующего юзера", description = "Запрос несуществующего юзера")
    public void getUserNotFound(){
        API_STEPS.getUserById(23, 404);
    }

    @Test(testName = "Проверка списка цветов", description = "Проверка списка цветов")
    public void getColorsList(){
        API_STEPS.getColorsList();
    }

    @Test(testName = "Проверка полученного цвета", description = "Проверка полученного цвета")
    public void getColorById(){
        ColorsResponse actualResponse = API_STEPS.getColorById(2, 200);
        DataColorResponse dataResponse = new DataColorResponse(2,"fuchsia rose",2001,"#C74375","17-2031");
        SupportResponse supportResponse = new SupportResponse("https://reqres.in/#support-heading","To keep ReqRes free, contributions towards server costs are appreciated!");
        ColorsResponse expectedResponse = new ColorsResponse(dataResponse, supportResponse);
        API_STEPS.checkColor(actualResponse, expectedResponse);
    }

    @Test(testName = "Запрос несуществующего цвета", description = "Запрос несуществующего цвета")
    public void getColorNotFound(){
        API_STEPS.getColorById(23, 404);
    }
}
