package pl.lg.charity.dtos;

import lombok.Data;
import pl.lg.charity.domain.entities.Category;
import pl.lg.charity.domain.entities.Institution;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    private String pickUpComment;
    @NotBlank
    private List<Category> categories = new ArrayList<>();
    @NotBlank
    private Institution institution;

}
