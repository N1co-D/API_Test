package petstore.petservice;

import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import petstore.services.PetService;

@Slf4j
public class PetServiceTestConfig extends PetServiceTestData {
    public final PetService petService = new PetService();

    @BeforeEach
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Description("Очистка данных после тестирование")
    public void cleanData(long petId) {
        petService.deletePetById(petId);
    }
}