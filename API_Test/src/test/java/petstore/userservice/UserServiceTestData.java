package petstore.userservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.provider.Arguments;
import petstore.data.user.User;

import java.util.List;
import java.util.stream.Stream;

public class UserServiceTestData {

    /**
     * TC-ID11
     */
    public static Stream<Arguments> checkCreateUserTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of("QAtester101", userData("QAtester101"), "QAtester101",
                "Vladimir", "Ivanov", "java@mail.ru", "password", "string", 0));
    }

    /**
     * TC-ID12
     */
    public static Stream<Arguments> checkCreateUserListTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(userListData()));
    }

    /**
     * TC-ID13
     */
    public static Stream<Arguments> checkGetUserByUsernameTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of("QAtester102", userData("QAtester102")));
    }

    /**
     * TC-ID14
     */
    public static Stream<Arguments> checkLoginTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of("QAtester103", userData("QAtester103"), "password"));
    }

    /**
     * TC-ID15
     */
    public static Stream<Arguments> checkUpdateUserTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of("QAtester104", userData("QAtester104"), "password", "QAtester105",
                updateUserData("QAtester105"), "QAtester105", "QAtester105", "Daniil", "Markevich",
                "qa@test.com", "password", "string", 0));
    }

    /**
     * TC-ID16
     */
    public static Stream<Arguments> checkLogoutTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of("QAtester106", userData("QAtester106"), "password"));
    }

    /**
     * TC-ID17
     */
    public static Stream<Arguments> checkDeleteUserByUsernameTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of("QAtester107", userData("QAtester107")));
    }

    private static String userData(String username) throws JsonProcessingException {
        User user = new User(0L, username, "Vladimir", "Ivanov", "java@mail.ru",
                "password", "string", 0);
        return turnObjectIntoJson(user);
    }

    private static String updateUserData(String updatedUsername) throws JsonProcessingException {
        User user = new User(0L, updatedUsername, "Daniil", "Markevich", "qa@test.com",
                "password", "string", 0);
        return turnObjectIntoJson(user);
    }

    private static String userListData() throws JsonProcessingException {
        List<User> users = List.of(
                new User(101101L, "Alexx", "Alex", "Petrov", "email",
                        "password", "string", 0),
                new User(202202L, "Maxx", "Max", "Ivanov", "email",
                        "password", "string", 0));
        return turnObjectIntoJson(users);
    }

    private static String turnObjectIntoJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }
}