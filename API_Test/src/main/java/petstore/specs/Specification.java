package petstore.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import petstore.utilites.ConfProperties;

public class Specification {

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(new ConfProperties().getProperty("base-url"))
                .log(LogDetail.METHOD)
                .build();
    }

    public static ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(200)
                .build();
    }
}