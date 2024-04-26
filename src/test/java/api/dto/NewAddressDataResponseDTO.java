package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewAddressDataResponseDTO {
    private String status;
    private NewAddressDataResponseDTO.Data data;

    @lombok.Data
    public static class Data {
        private String network;
        private String address;
        private String label;
    }
}
