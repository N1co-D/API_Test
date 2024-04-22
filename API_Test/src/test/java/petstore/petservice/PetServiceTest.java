package petstore.petservice;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import petstore.data.pet.Category;
import petstore.data.pet.Tag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PetServiceTest extends PetServiceTestConfig {

    @DisplayName("TC-ID1")
    @Description("TC-ID1 Отправка запроса на добавление нового питомца")
    @ParameterizedTest
    @MethodSource("petstore.petservice.PetServiceTestData#checkAddNewPetTestData")
    public void checkAddNewPet(long petId, String petJson, long expectedId, Category expectedCategory, String expectedName,
                               List<String> expectedPhotoUrls, List<Tag> expectedTags, String expectedStatus) {
        petService
                .addNewPet(petId, petJson)
                .checkPetParameters(petId, expectedId, expectedCategory, expectedName,
                        expectedPhotoUrls, expectedTags, expectedStatus);
        cleanData(petId);
    }

    @DisplayName("TC-ID2")
    @Description("TC-ID2 Отправка запроса на получение питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.petservice.PetServiceTestData#checkFindPetByIdTestData")
    public void checkFindPetById(long petId, String petJson) {
        petService
                .addNewPet(petId, petJson)
                .findPetById(petId);
        cleanData(petId);
    }

    @DisplayName("TC-ID3")
    @Description("TC-ID3 Отправка запроса на изменение имени и статуса питомца")
    @ParameterizedTest
    @MethodSource("petstore.petservice.PetServiceTestData#checkPartialUpdatePetTestData")
    public void checkPartialUpdatePet(long petId, String petJson, String updatedName, String updatedStatus) {
        petService
                .addNewPet(petId, petJson)
                .partialUpdatePet(petId, updatedName, updatedStatus)
                .checkPetsNameAndStatus(petId, updatedName, updatedStatus);
        cleanData(petId);
    }

    @DisplayName("TC-ID4")
    @Description("TC-ID4 Отправка запроса на изменение данных о питомце")
    @ParameterizedTest
    @MethodSource("petstore.petservice.PetServiceTestData#checkFullUpdatePetTestData")
    public void checkFullUpdatePet(long petId, String petJson, String updatePetJson, long expectedId, Category expectedCategory,
                                   String expectedName, List<String> expectedPhotoUrls, List<Tag> expectedTags,
                                   String expectedStatus) {
        petService
                .addNewPet(petId, petJson)
                .fullUpdatePet(updatePetJson)
                .checkPetParameters(petId, expectedId, expectedCategory, expectedName,
                        expectedPhotoUrls, expectedTags, expectedStatus);
        cleanData(petId);
    }

    @DisplayName("TC-ID5")
    @Description("TC-ID5 Отправка запроса на удаление питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.petservice.PetServiceTestData#checkDeletePetByIdTestData")
    public void checkDeletePetById(long petId, String petJson) {
        petService
                .addNewPet(petId, petJson)
                .deletePetById(petId);
        assertTrue(petService.checkNoDataAboutPet(petId),
                "Питомец с заданным id = " + petId + " не был удален");
    }

    @Description("TC-ID6 Отправка запроса на получение всех питомцев по статусу")
    @ParameterizedTest
    @ValueSource(strings = {"available", "pending", "sold"})
    public void checkFindPetByStatus(String status) {
        petService.findPetByStatus(status);
    }
}