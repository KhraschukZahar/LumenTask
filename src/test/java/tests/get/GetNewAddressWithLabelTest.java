package tests.get;

import api.clients.NewAddressWithLabelClient;
import api.dto.NewAddressDataResponseDTO;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.BaseTest;
import tests.TestData;

public class GetNewAddressWithLabelTest extends BaseTest {

    @Test
    @Description("Positive: Creation of a new address with a label.")
    public void getNewAddressWithLabelTest() {
        NewAddressWithLabelClient client = new NewAddressWithLabelClient("https://block.io");
        NewAddressDataResponseDTO response = client.generateNewAddressWithLabel(NewAddressWithLabelClient.constructDefaultQueryParams());

        softAssert.assertEquals(response.getStatus(), TestData.EXPECTED_STATUS, "Status isn`t correct");
        softAssert.assertEquals(response.getData().getAddress(), TestData.EXPECTED_ADDRESS, "Address isn`t correct");
        softAssert.assertEquals(response.getData().getNetwork(), TestData.EXPECTED_NETWORK, "Network isn`t correct");
        softAssert.assertEquals(response.getData().getLabel(), TestData.EXPECTED_LABEL, "Label isn`t correct");
        softAssert.assertAll();
    }
}
