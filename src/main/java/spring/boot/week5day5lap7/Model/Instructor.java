package spring.boot.week5day5lap7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instructor {

    @NotNull(message = "Id shouldn't be null")
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 20,message = "Name must be greater than 2 & less than 20 character ")
    private String name;

    @NotEmpty(message = "Email shouldn't be empty ")
    @Email(message = "Email should be valid ")
    private String email;

    @NotEmpty(message = "phone number shouldn't be empty")
    @Pattern(regexp = "^\\+?[0-9 .()-]{10,25}$", message = "Phone number should be valid")
    private String phoneNumber;

    @NotNull
    @Min(value = 0, message = "Years of experience should be at least 0")
    private int yearsOfExperience;
}
