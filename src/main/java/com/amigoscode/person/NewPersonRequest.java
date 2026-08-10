package com.amigoscode.person;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPersonRequest(@NotEmpty(message = "name can't be empty") String name,
                               @Min(value = 16, message = "age must be at least 16") Integer age,
                               @NotNull(message = "gender can't be null") Gender gender,
                               @Email(message = "email must be a valid email address") String email) {

}
