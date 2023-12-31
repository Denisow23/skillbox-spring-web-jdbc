package ru.denisov.contactBookWeb.service;

import ru.denisov.contactBookWeb.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(Long id);

    void createOrReplace(Contact contact);
}
