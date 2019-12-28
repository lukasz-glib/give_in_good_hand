package pl.lg.charity.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryDataDTO {

    @NotBlank
    @Size(max = 60)
    private String name;
    private Long id;
}
