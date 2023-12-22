package ru.denisov.contactBookWeb.repository;

import org.springframework.data.jdbc.repository.query.Query;
import ru.denisov.contactBookWeb.entity.Contact;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository {
    List<Contact> findAll();

    Optional<Contact> findById(Long id);

    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(Long id);


}
