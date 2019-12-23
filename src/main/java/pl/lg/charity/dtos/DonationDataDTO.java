package pl.lg.charity.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
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

    private Long quantity;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
    private List<Category> categories = new ArrayList<>();
    private Institution institution;

}
