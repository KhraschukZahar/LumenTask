package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTransactionsResponseDTO {
    private String status;
    private Data data;

    @lombok.Data
    public static class Data {
        private String network;
        private ArrayList<Tx> txs;

        @lombok.Data
        public static class Tx {
            private String txid;
            private boolean from_green_address;
            private int time;
            private int confirmations;
            private ArrayList<AmountsReceived> amounts_received;
            private ArrayList<String> senders;
            private double confidence;
            private Object propagated_by_nodes;

            @lombok.Data
            public static class AmountsReceived {
                private String recipient;
                private String amount;


            }
        }
    }
}