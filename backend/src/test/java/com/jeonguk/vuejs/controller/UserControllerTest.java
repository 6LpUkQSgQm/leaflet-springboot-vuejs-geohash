package com.jeonguk.vuejs.controller;

import com.google.gson.JsonObject;
import com.jeonguk.vuejs.controller.rest.UserController;
import com.jeonguk.vuejs.SpringBootVuejsApplication;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = SpringBootVuejsApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class UserControllerTest {

	@LocalServerPort
	private int port;

	private static final String APPLICATION_JSON = "application/json";

	@Before
    public void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

	@Test
	public void saysHello() {
		when()
			.get("/api/hello")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.assertThat()
			.body("data", equalTo(UserController.HELLO_TEXT));
	}

	@Test
    public void addNewUserAndRetrieveItBack() {
        //User norbertSiegmund = new User("Norbert", "Siegmund");

		JsonObject userDto = new JsonObject();
		userDto.addProperty("first_name", "Jeonguk");
		userDto.addProperty("last_name", "Lee");

		JsonPath retrievedUser =
            given()
	            .header("Content-Type", APPLICATION_JSON)
	            .body(userDto.toString())
            .when()
                .post("/api/user")
            .then()
                .statusCode(is(HttpStatus.SC_CREATED))
                .extract()
	            .jsonPath();
		log.info("User retrievedUser {}", retrievedUser.getLong("id"));

		JsonPath responseUser =
            given()
					.header("Content-Type", APPLICATION_JSON)
                    .pathParam("id", retrievedUser.getLong("id"))
                .when()
                    .get("/api/user/{id}")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .assertThat()
                        .extract().jsonPath();
		log.info("User responseUser {}", responseUser);
	    // Did Norbert came back?
        assertThat(responseUser.getString("first_name"), is("Jeonguk"));
        assertThat(responseUser.getString("last_name"), is("Lee"));
    }

}
