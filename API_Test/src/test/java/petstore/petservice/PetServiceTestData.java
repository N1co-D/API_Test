package petstore.petservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.provider.Arguments;
import petstore.data.pet.Category;
import petstore.data.pet.Pet;
import petstore.data.pet.Tag;

import java.util.List;
import java.util.stream.Stream;

public class PetServiceTestData {
    public final static List<String> PHOTO_URLS = List.of("url1", "url2", "url3");
    public final static List<Tag> TAGS = List.of(new Tag(0L, "black"), new Tag(1L, "small"));
    public final static Category CATEGORY = new Category(0L, "dog");

    /**
     * TC-ID1
     */
    public static Stream<Arguments> checkAddNewPetTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(101101L, petData(101101L), 101101L, CATEGORY, "doggie",
                PHOTO_URLS, TAGS, "available"));
    }

    /**
     * TC-ID2
     */
    public static Stream<Arguments> checkFindPetByIdTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(101102L, petData(101102L)));
    }

    /**
     * TC-ID3
     */
    public static Stream<Arguments> checkPartialUpdatePetTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(101103L, petData(101103L), "Kesha", "sold"));
    }

    /**
     * TC-ID4
     */
    public static Stream<Arguments> checkFullUpdatePetTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(101104L, petData(101104L), updatePetData(101104L), 101104L,
                CATEGORY, "boris", PHOTO_URLS, TAGS, "sold"));
    }

    /**
     * TC-ID5
     */
    public static Stream<Arguments> checkDeletePetByIdTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(101105L, petData(101105L)));
    }

    /**
     * TC-ID7
     */
    public static Stream<Arguments> checkValidateJsonSchemeTestData() throws JsonProcessingException {
        return Stream.of(Arguments.of(101106L, petData(101106L),
                "src/main/java/petstore/schemes/petscheme.json"));
    }

    private static String petData(long petId) throws JsonProcessingException {
        Pet pet = new Pet(petId, CATEGORY, "doggie", PHOTO_URLS, TAGS, "available");
        return turnObjectIntoJson(pet);
    }

    private static String updatePetData(long petId) throws JsonProcessingException {
        Pet pet = new Pet(petId, CATEGORY, "boris", PHOTO_URLS, TAGS, "sold");
        return turnObjectIntoJson(pet);
    }

    private static String turnObjectIntoJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }
}