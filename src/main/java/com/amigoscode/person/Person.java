package com.amigoscode.person;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Person(Integer id,
                     @NotNull String name,
                     @Min(16) Integer age,
                     Gender gender,
                     String email) {

}
