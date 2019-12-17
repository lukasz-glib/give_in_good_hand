package pl.lg.charity.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DonationDataDTO {

    @NotBlank
    @Size(min = 1)
    private Long quantity;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    @NotBlank
    private LocalDate pickUpDate;
    @NotBlank
    private LocalTime pickUpTime;
    @NotBlank
    private String pickUpComment;

}
