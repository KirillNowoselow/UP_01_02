import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Generator;

import java.util.ArrayList;
import java.util.List;

@Epic("Тестовая группа 1")
@Feature("Тесты списка пользователей")
public class UserTest extends BaseTest {
    public static List<User> users = new ArrayList<>();

    @BeforeClass(description = "Создание списка пользователей")
    public static void createUser(){
        for (int i = 0; i<5; i++){
            users.add(new User(
                    Generator.generateLogin(7),
                    Generator.generatePassword(8),
                    Generator.generateEmail()));
        }
    }

    @Test(testName = "Проверка данных о пользователях", description = "Проверка данных о пользователях")
    public void userTest(){
        USER_STEP.checkListUsers(users);
        USER_STEP.checkValidationEmail(users);
        USER_STEP.checkValidationPassword(users);
    }
}
