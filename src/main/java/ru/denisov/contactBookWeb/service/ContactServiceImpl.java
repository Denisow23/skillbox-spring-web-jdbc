package ru.denisov.contactBookWeb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.denisov.contactBookWeb.entity.Contact;
import ru.denisov.contactBookWeb.exceptions.ContactNotFoundException;
import ru.denisov.contactBookWeb.repository.ContactRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{

    private final ContactRepository contactRepository;


    @Override
    public List<Contact> findAll() {
        log.debug("Call ContactServiceImpl -> findAll()");

        return contactRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        log.debug("Call ContactServiceImpl -> findById()");

        return contactRepository.findById(id).orElseThrow();
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Call ContactServiceImpl -> save()");

        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("Call ContactServiceImpl -> update()");

        return contactRepository.update(contact);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call ContactServiceImpl -> deleteById()");

        contactRepository.deleteById(id);
    }

    @Override
    public void createOrReplace(Contact contact){
        log.debug("Call ContactServiceImpl -> createOrReplace()");

        if (contact.getId() != null) {
            contactRepository.update(contact);
        } else {
            contactRepository.save(contact);
        }
    }
}
