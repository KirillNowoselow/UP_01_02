package steps;

import io.qameta.allure.Step;
import models.User;
import org.testng.Assert;

import java.util.List;

public class UserStep {

    @Step("Проверка того, что список пользователей не пустой")
    public void checkListUsers(List<User> users){
        Assert.assertTrue(users.size()!=0);
    }
    @Step("Проверка валидности пароля")
    public void checkValidationPassword(List<User> users){
        for(int i = 0; i<users.size();i++){
            Assert.assertTrue(users.get(i).getPassword().length()>=8);
            Assert.assertTrue(users.get(i).getPassword().matches(".*[A-Z].*"));
        };
    }
    @Step("Проверка валидности почты")
    public void checkValidationEmail(List<User> users){
        for(int i = 0; i<users.size();i++){
            Assert.assertTrue(users.get(i).getEmail().contains("@gmail.com"));
        };
    }
}
