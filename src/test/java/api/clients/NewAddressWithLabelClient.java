package api.clients;

import api.dto.NewAddressDataResponseDTO;
import io.qameta.allure.Step;

import java.util.Map;

import static api.clients.ConfigProvider.API_KEY;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class NewAddressWithLabelClient extends AbstractBaseRestClient {
    public NewAddressWithLabelClient(String url) {
        super(url);
    }

    public static Map<String, String> constructDefaultQueryParams() {
        return Map.of("api_key", ConfigProvider.API_KEY, "type", "received");
    }

    @Step("Get new address with set label")
    public NewAddressDataResponseDTO generateNewAddressWithLabel(Map<String, String> queryParams) {
        String label = "MyNewAddress";

        return given()
                .spec(requestSpec)
                .queryParams(queryParams)
                .when()
                .param("api_key", API_KEY)
                .param("label", label)
                .when()
                .get("/api/v2/get_new_address")
                .then()
                .statusCode(HTTP_OK)
                .extract().as(NewAddressDataResponseDTO.class);
    }
}
