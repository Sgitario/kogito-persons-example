package org.sgitario.kogito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class CheckAgeResourceTest {

	@Test
	public void testPass() {
		given()
			.body("{\"name\":\"Juan\", \"age\": " + 20 + "}")
			.contentType(ContentType.JSON)
			.when().post("/check-age")
			.then().statusCode(200).body(is("true"));
	}

	@Test
	public void testShouldFail() {
		given()
		.body("{\"name\":\"Juan\", \"age\": " + 15 + "}")
		.contentType(ContentType.JSON)
		.when().post("/check-age")
		.then().statusCode(200).body(is("false"));
	}

}