package com.example.demo.validation;

import com.example.demo.model.Utilisateur;
import com.example.demo.repository.UserRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepo userRepository;

    public UniqueEmailValidator (
            final UserRepo userRepository
    ) {
        this.userRepository = userRepository;
    }



    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Utilisateur foundEmail = userRepository.findUserByEmail(email);

        return foundEmail == null;
    }
}
