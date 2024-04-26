package api.clients;

import api.dto.WalletBalanceResponseDTO;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Map;

import static api.clients.ConfigProvider.API_KEY;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class GetBalanceClient extends AbstractBaseRestClient {
    public GetBalanceClient(String url) {
        super(url);
    }

    public static Map<String, String> constructDefaultQueryParams() {
        return Map.of("api_key", ConfigProvider.API_KEY, "type", "received");
    }

    @Step("Returns the balance of your entire coins")
    public WalletBalanceResponseDTO getBalance(Map<String, String> queryParams, String walletAddress) {
        return given()
                .spec(requestSpec)
                .queryParams(queryParams)
                .when()
                .param("api_key", API_KEY)
                .param("addresses", walletAddress)
                .when()
                .get("/api/v2/get_balance")
                .then()
                .statusCode(HTTP_OK)
                .extract().as(WalletBalanceResponseDTO.class);
    }

    public boolean didBalanceChangedDuring(double startBalance, Map<String, String> queryParams, String walletAddress, int iterationTimeOut, int failureTimeOut) throws InterruptedException {
        int secondsPast = 0;
        boolean isChanged = false;
        while (secondsPast < failureTimeOut) {
            WalletBalanceResponseDTO response = getBalance(queryParams, walletAddress);
            double currentBalance = Double.parseDouble(response.getData().getBalances().get(0).getAvailable_balance());
            isChanged = currentBalance != startBalance;
            if (isChanged) break;
            System.out.println("Balance didn't change within " + secondsPast + " seconds");
            Thread.sleep(Duration.ofSeconds(iterationTimeOut).toMillis());
            secondsPast += iterationTimeOut;
        }
        return isChanged;
    }

}
