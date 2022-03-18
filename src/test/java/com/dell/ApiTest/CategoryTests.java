package com.dell.ApiTest;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dell.util.Util;

import java.io.FileNotFoundException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

public class CategoryTests {

    private JsonObject apiInfo;

    @BeforeClass
    public void init() throws FileNotFoundException {
        apiInfo = Util.getJsonObject("src/main/resources/ApiInfo.json");
    }

    @BeforeMethod
    public void findBaseUri() {
        RestAssured.baseURI = apiInfo.get("baseUrl").getAsString();
    }
    /**
     * A test case to validate the json schema response
     */
    @Test(priority = 1)
    public void validateResponseSchemaFromGetCategoriesAPI() {
        RestAssured
                .when().
                    get(apiInfo.get("endPoint").getAsString())
                .then().assertThat().
                    body(matchesJsonSchemaInClasspath("schema.json")).
                statusCode(HttpStatus.SC_OK);
    }
    /**
     * A test case to test the get request with the default limit and skip query parameters
     */
    @Test(priority = 2)
    public void testFindCategoriesAPIWithDefaultLimitAndSkipQueryParam() {
        RestAssured
                .given().
                    get(apiInfo.get("endPoint").getAsString())
                .then().
                    statusCode(HttpStatus.SC_OK).
                    body("limit", equalTo(10)).
                    body("skip", equalTo(0)).
                    body("data.size()", is(10)).
                    body("data[0].subCategories.size()", is(8)).
                    body("data[0].id", notNullValue()).
                    body("data[0].name", notNullValue());
    }
    /**
     * A test case to test the get request with a custom limit query parameter
     */
    @Test(priority = 3)
    public void testFindCategoriesAPIWithCustomLimitQueryParam() {
        RestAssured
                .given().
                    get(apiInfo.get("queryParameterLimit").getAsString())
                .then().
                    statusCode(HttpStatus.SC_OK).
                    body("limit", equalTo(apiInfo.get("limit").getAsInt())).
                    body("skip", equalTo(0)).
                    body("data.size()", is(apiInfo.get("limit").getAsInt())).
                    body("data[0].subCategories.size()", is(8)).
                    body("data[0].id", notNullValue()).
                    body("data[0].name", notNullValue());
    }
    /**
     * A test case to test the get request with a custom skip query parameter
     */
    @Test(priority = 4)
    public void testFindCategoriesAPIWithCustomSkipQueryParam() {
        RestAssured
                .given().
                    get(apiInfo.get("queryParameterSkip").getAsString())
                .then().
                    statusCode(HttpStatus.SC_OK).
                    body("limit", equalTo(10)).
                    body("skip", equalTo(apiInfo.get("skip").getAsInt())).
                    body("data.size()", is(10)).
                    body("data[0].subCategories.size()", is(0)).
                    body("data[0].id", notNullValue()).
                    body("data[0].name", notNullValue());
    }
    /**
     * A test case to test the get request with a valid limit and skip query parameter
     */
    @Test(priority = 5)
    public void testFindCategoriesAPIWithValidLimitAndSkipQueryParam() {
        RestAssured
                .given().
                    get(apiInfo.get("validLimitAndSkipQueryParam").getAsString())
                .then().
                    statusCode(HttpStatus.SC_OK).
                    body("limit", equalTo(apiInfo.get("limit").getAsInt())).
                    body("skip", equalTo(apiInfo.get("skip").getAsInt())).
                    body("data.size()", is(apiInfo.get("limit").getAsInt())).
                    body("data[0].subCategories.size()", is(0)).
                    body("data[0].id", notNullValue()).
                    body("data[0].name", notNullValue());
    }
    /**
     * A test case to test the get request with an invalid limit and valid skip query parameter
     */
    @Test(priority = 6)
    public void testFindCategoriesAPIWithInvalidLimitAndValidSkipQueryParam() {
        RestAssured
                .given().
                    get(apiInfo.get("InvalidLimitAndSkipQueryParam").getAsString())
                .then().
                    statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                    body("message", notNullValue());
    }
    /**
     * A test case to test the get request with an invalid limit query parameter
     */
    @Test(priority = 7)
    public void testFindCategoriesAPIWithInvalidLimitQueryParam() {
        RestAssured
                .given().
                    get(apiInfo.get("InvalidLimitQueryParam").getAsString())
                .then().
                    statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                    body("message", notNullValue());
    }
    /**
     * A test case to test the get request with an invalid skip query parameter
     */
    @Test(priority = 8)
    public void testFindCategoriesAPIWithInvalidSkipQueryParam() {
        RestAssured
                .given().
                    get(apiInfo.get("InvalidSkipQueryParam").getAsString())
                .then().
                    statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                    body("message", notNullValue());
    }
    /**
     * A test case to test the post request with a valid request body
     */
    @Test(priority = 9)
    public void testCreateCategoryAPIWithValidRequestBody() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(apiInfo.get("CategoriesPostBody").getAsString())
                .when()
                    .post(apiInfo.get("endPoint").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_CREATED);
    }
    /**
     * A test case to test the post request in case of a duplicate
     */
    @Test(priority = 10)
    public void testCreateCategoryAPIWithUniqueViolation() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(apiInfo.get("CategoriesPostBody").getAsString())
                .when()
                    .post(apiInfo.get("endPoint").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    /**
     * A test case to test the post request without ID
     */
    @Test(priority = 11)
    public void testCreateCategoryAPIWithoutId() {
        RestAssured
                .given()
                    .accept(ContentType.JSON)
                    .body(apiInfo.get("createCategoryWithoutId").getAsString())
                .when()
                    .post(apiInfo.get("endPoint").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    /**
     * A test case to test the post request without name
     */
    @Test(priority = 12)
    public void testCreateCategoryAPIWithoutName() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(apiInfo.get("createCategoryWithoutName").getAsString())
                .when()
                    .post(apiInfo.get("endPoint").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    /**
     * A test case to test the post request with an empty request body
     */
    @Test(priority = 13)
    public void testCreateCategoryAPIWithoutRequestBody() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(apiInfo.get("createCategoryWithEmptyRequestBody").getAsString())
                .when()
                    .post(apiInfo.get("endPoint").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    /**
     * A test case to test the post request with an invalid request body
     */
    @Test(priority = 14)
    public void testCreateCategoryAPIWithInvalidRequestBody() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(apiInfo.get("createCategoryWithInvalidRequestBody").getAsString())
                .when()
                    .post(apiInfo.get("endPoint").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    /**
     * A test case to test the patch request with a valid request body and a valid id
     */
    @Test(priority = 15)
    public void testPatchCategoryAPI() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(apiInfo.get("updateName").getAsString())
                .when()
                    .patch(apiInfo.get("id").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }
    /**
     * A test case to test the patch request with an invalid id and a valid request body
     */
    @Test(priority = 16)
    public void testPatchCategoryAPIWithInvalidId() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(apiInfo.get("updateName").getAsString())
                .when()
                    .patch( apiInfo.get("invalidId").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
    }
    /**
     * A test case to test the patch request with an invalid request body and a valid id
     */
    @Test(priority = 17)
    public void testPatchCategoryAPIWithInvalidRequestBody() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(apiInfo.get("patchCategoryWithInvalidRequestBody").getAsString())
                .when()
                    .patch( apiInfo.get("id").getAsString())
                    .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("message", notNullValue());;
    }
    /**
     * A test case to test the patch request with an empty request body and a valid id
     */
    @Test(priority = 18)
    public void testPatchCategoryAPIWithEmptyRequestBody() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(apiInfo.get("patchWithEmptyBody").getAsString())
                .when()
                    .patch( apiInfo.get("id").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }
    /**
     * A test case to test the delete request with a valid id
     */
    @Test(priority = 20)
    public void testDeleteCategoryAPI() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .delete(apiInfo.get("id").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }
    /**
     * A test case to test the delete request with an invalid id
     */
    @Test(priority = 21)
    public void testDeleteCategoryAPIWithNotFoundCategory() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .delete(apiInfo.get("invalidId").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
    }
    /**
     * A test case to test the get request with a valid id
     */
    @Test(priority = 19)
    public void testFindCategoryByIdAPI() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .get(apiInfo.get("id").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("name", (equalTo(apiInfo.get("updatedNameInCategory").getAsString())));
    }
    /**
     * A test case to test the get request with an invalid id
     */
    @Test(priority = 22)
    public void testFindCategoryAPIWithNotFoundCategory() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .get(apiInfo.get("invalidId").getAsString())
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
