package ru.petkov.bookcompany.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Embeddable
@Getter
@Setter
public class Contact {

    private String email;
    private String phone;
}
