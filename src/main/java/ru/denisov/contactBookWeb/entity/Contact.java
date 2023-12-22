package ru.denisov.contactBookWeb.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Data
@FieldNameConstants
public class Contact {

    @Id
    private Long id;

    @Column(value = "first_name")
    private String firstName;

    @Column(value = "last_name")
    private String lastName;

    private String email;

    private String phone;

}
