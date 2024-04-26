package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalletBalanceResponseDTO {
    private String status;
    private Data data;

    @lombok.Data
    public static class Data {
        private String network;
        private String available_balance;
        private String pending_received_balance;
        private ArrayList<Balance> balances;

        @lombok.Data
        public static class Balance {
            private int user_id;
            private String label;
            private String address;
            private String available_balance;
            private String pending_received_balance;
        }

    }
}

