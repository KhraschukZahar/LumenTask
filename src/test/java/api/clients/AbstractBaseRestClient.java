package api.clients;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static api.clients.ConfigProvider.API_KEY;

public abstract class AbstractBaseRestClient {
    protected final RequestSpecification requestSpec;
    protected final RequestSpecification invalidRequestSpec;

    public AbstractBaseRestClient(String url) {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        requestSpec = RestAssured.given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParam("api_key", API_KEY)
                .log().all();

        invalidRequestSpec = RestAssured.given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParam("api_key", "invalid_api_key")
                .log().all();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        if (url.matches("^(https)://.*$")) {
            requestSpec.relaxedHTTPSValidation();
        }
    }
}
