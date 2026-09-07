package com.amigoscode.person;

import com.amigoscode.validation.Foo;
import jakarta.validation.constraints.*;

public record NewPersonRequest(
        @NotNull @NotEmpty String name,
        @Positive @Min(16) Integer age,
        @NotNull Gender gender,
        @Email String email
) {
}
