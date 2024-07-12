package ru.petkov.bookcompany.dto;

import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;
import ru.petkov.bookcompany.entity.Contact;

@Getter
@Setter
public class ClientDTO {

    private Long clientId;
    private String firstName;
    private String lastName;
    private Contact contact;

}
