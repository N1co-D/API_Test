package petstore;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import petstore.data.pet.Category;
import petstore.data.pet.Tag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PetServiceTest extends PetStoreTestConfig {

    @DisplayName("TC-ID1")
    @Description("TC-ID1 Отправка запроса на добавление нового питомца")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkAddNewPetTestData")
    public void checkAddNewPet(String petJson, long expectedId, Category expectedCategory, String expectedName,
                               List<String> expectedPhotoUrls, List<Tag> expectedTags, String expectedStatus) {
        petService
                .addNewPet(PET_ID, petJson)
                .checkPetParameters(PET_ID, expectedId, expectedCategory, expectedName,
                        expectedPhotoUrls, expectedTags, expectedStatus);
        clearPetDataAfterTest(PET_ID);
    }

    @DisplayName("TC-ID2")
    @Description("TC-ID2 Отправка запроса на получение питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFindPetByIdTestData")
    public void checkFindPetById(String petJson) {
        petService
                .addNewPet(PET_ID, petJson)
                .findPetById(PET_ID);
        clearPetDataAfterTest(PET_ID);
    }

    @DisplayName("TC-ID3")
    @Description("TC-ID3 Отправка запроса на изменение имени и статуса питомца")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkPartialUpdatePetTestData")
    public void checkPartialUpdatePet(String petJson, String updatedName, String updatedStatus) {
        petService
                .addNewPet(PET_ID, petJson)
                .partialUpdatePet(PET_ID, updatedName, updatedStatus)
                .checkPetsNameAndStatus(PET_ID, updatedName, updatedStatus);
        clearPetDataAfterTest(PET_ID);
    }

    @DisplayName("TC-ID4")
    @Description("TC-ID4 Отправка запроса на изменение данных о питомце")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkFullUpdatePetTestData")
    public void checkFullUpdatePet(String petJson, String updatePetJson, long expectedId, Category expectedCategory,
                                   String expectedName, List<String> expectedPhotoUrls, List<Tag> expectedTags,
                                   String expectedStatus) {
        petService
                .addNewPet(PET_ID, petJson)
                .fullUpdatePet(updatePetJson)
                .checkPetParameters(PET_ID, expectedId, expectedCategory, expectedName,
                        expectedPhotoUrls, expectedTags, expectedStatus);
        clearPetDataAfterTest(PET_ID);
    }

    @DisplayName("TC-ID5")
    @Description("TC-ID5 Отправка запроса на удаление питомца по id")
    @ParameterizedTest
    @MethodSource("petstore.PetStoreTestData#checkDeletePetByIdTestData")
    public void checkDeletePetById(String petJson) {
        petService
                .addNewPet(PET_ID, petJson)
                .deletePetById(PET_ID);
        assertTrue(petService.checkNoDataAboutPet(PET_ID),
                "Питомец с заданным id = " + PET_ID + " не был удален");
    }

    @DisplayName("TC-ID6")
    @Description("TC-ID6 Отправка запроса на получение всех питомцев по статусу")
    @ParameterizedTest
    @ValueSource(strings = {"available", "pending", "sold"})
    public void checkFindPetByStatus(String status) {
        petService.findPetByStatus(status);
    }

    @DisplayName("TC-ID7")
    @Description("TC-ID7 Отправка запроса на получение данных о количестве питомцев в инвентаре по статусам")
    @Test
    public void checkGetInventoryByStatus() {
        storeService
                .getInventoryWithStatusFilter();
    }
}