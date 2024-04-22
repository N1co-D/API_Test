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
        long petId = 101101L;
        return Stream.of(Arguments.of(petId, petData(petId), petId, CATEGORY, "doggie",
                PHOTO_URLS, TAGS, "available"));
    }

    /**
     * TC-ID2
     */
    public static Stream<Arguments> checkFindPetByIdTestData() throws JsonProcessingException {
        long petId = 101102L;
        return Stream.of(Arguments.of(petId, petData(petId)));
    }

    /**
     * TC-ID3
     */
    public static Stream<Arguments> checkPartialUpdatePetTestData() throws JsonProcessingException {
        long petId = 101103L;
        return Stream.of(Arguments.of(petId, petData(petId), "Kesha", "sold"));
    }

    /**
     * TC-ID4
     */
    public static Stream<Arguments> checkFullUpdatePetTestData() throws JsonProcessingException {
        long petId = 101104L;
        return Stream.of(Arguments.of(petId, petData(petId), updatePetData(petId), petId, CATEGORY,
                "boris", PHOTO_URLS, TAGS, "sold"));
    }

    /**
     * TC-ID5
     */
    public static Stream<Arguments> checkDeletePetByIdTestData() throws JsonProcessingException {
        long petId = 101105L;
        return Stream.of(Arguments.of(petId, petData(petId)));
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