package api.clients;

import api.dto.GetTransactionsResponseDTO;
import io.qameta.allure.Step;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class ReceivedTransactionsRestTestClient extends AbstractBaseRestClient {

    public ReceivedTransactionsRestTestClient(String url) {
        super(url);
    }

    @Step("Getting recent received transactions")
    public GetTransactionsResponseDTO getRecentReceivedTransactions(Map<String, String> queryParams) {
        return given()
                .spec(requestSpec)
                .queryParams(queryParams)
                .when()
                .get("/api/v2/get_transactions")
                .then()
                .statusCode(HTTP_OK)
                .extract().as(GetTransactionsResponseDTO.class);
    }

    public static Map<String, String> constructDefaultQueryParams() {
        return Map.of("api_key", ConfigProvider.API_KEY, "type", "received");
    }
}
