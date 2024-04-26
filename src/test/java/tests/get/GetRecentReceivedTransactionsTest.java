package tests.get;

import api.clients.ReceivedTransactionsRestTestClient;
import api.dto.GetTransactionsResponseDTO;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.BaseTest;
import tests.TestData;

public class GetRecentReceivedTransactionsTest extends BaseTest {

    @Test
    @Description("Positive: Verify recent received transactions")
    public void recentTransactionsReturned() {
        ReceivedTransactionsRestTestClient client = new ReceivedTransactionsRestTestClient("https://block.io");
        GetTransactionsResponseDTO response = client.getRecentReceivedTransactions(ReceivedTransactionsRestTestClient.constructDefaultQueryParams());

        softAssert.assertEquals(response.getStatus(), TestData.EXPECTED_STATUS, "Status isn`t correct");
        softAssert.assertEquals(response.getData().getNetwork(), TestData.EXPECTED_NETWORK, "Network isn`t correct");

        response.getData().getTxs().stream().findFirst().ifPresent(tx -> {
            softAssert.assertEquals(tx.getTxid(), TestData.EXPECTED_TXID, "Txid isn`t correct");
            softAssert.assertEquals(tx.isFrom_green_address(), TestData.EXPECTED_FROM_GREEN_ADDRESS, "FromGreenAddress isn`t correct");
            softAssert.assertEquals(tx.getTime(), TestData.EXPECTED_TIME, "Time isn`t correct");

            tx.getAmounts_received().stream().findFirst().ifPresent(amount -> {
                softAssert.assertEquals(amount.getAmount(), TestData.EXPECTED_AMOUNT, "Amount isn`t correct");
            });
            softAssert.assertEquals(tx.getSenders().get(0), TestData.EXPECTED_SENDER, "Sender isn`t correct");
            softAssert.assertEquals(tx.getConfidence(), TestData.EXPECTED_CONFIDENCE, "Confidence isn`t correct");
            softAssert.assertEquals(tx.getPropagated_by_nodes(), TestData.EXPECTED_PROPAGATED_BY_NODES, "PropagatedByNodes isn`t correct");
        });

        softAssert.assertAll();
    }
}
