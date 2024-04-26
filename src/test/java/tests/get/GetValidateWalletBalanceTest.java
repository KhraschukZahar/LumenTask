package tests.get;

import api.clients.GetBalanceClient;
import api.dto.WalletBalanceResponseDTO;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTest;

public class GetValidateWalletBalanceTest extends BaseTest {

    private String testAddress;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://block.io";
        testAddress = "2NATS4m1jkSGnWUrSrQeTdDTZLZ4DX4Fv8p";
    }

    @Test
    @Description("Positive: Verify returns the balance of your entire Bitcoin")
    public void initialBalance() throws InterruptedException {
        GetBalanceClient client = new GetBalanceClient("https://block.io");

        WalletBalanceResponseDTO response = client.getBalance(GetBalanceClient.constructDefaultQueryParams(), testAddress);

        double startBalance = Double.parseDouble(response.getData().getBalances().get(0).getAvailable_balance());

        softAssert.assertTrue(client.didBalanceChangedDuring(startBalance, GetBalanceClient.constructDefaultQueryParams(), testAddress, 5, 60));
        softAssert.assertAll();
    }
}
