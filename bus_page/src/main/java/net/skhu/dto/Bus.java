package net.skhu.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Bus {
	int id;

    @NotEmpty
    @NotBlank
    @Size(min=3, max=6)
    String busNo;

    @NotEmpty
    @NotBlank
    String firstStop;
    String lastStop;

    int categoryId;
    String firstBus;
    String lastBus;

    String categoryName;

}
