package spring.boot.week5day5lap7.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Classroom {

    @NotNull(message = "Id shouldn't be null")
    private int id;

    @NotEmpty(message = "name shouldn't be empty")
    @Size(min = 2, max = 20, message = "Name must be greater than 2 & less than 20 character")
    private String name;

    @NotNull(message = "Capacity should not be null")
    @Min(value = 1, message = "Capacity should be at least 1")
    private int capacity;

    @NotEmpty(message = "Location should not be empty")
    private String location;

    @NotNull(message = "Is Available should not be null")
    private boolean isAvailable;

}
