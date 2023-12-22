package ru.denisov.contactBookWeb.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;
import ru.denisov.contactBookWeb.entity.Contact;
import ru.denisov.contactBookWeb.exceptions.ContactNotFoundException;
import ru.denisov.contactBookWeb.repository.mappers.ContactRowMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContactRepositoryImpl implements ContactRepository{

    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Contact> findAll() {
        log.debug("Calling findAll from ContactRepositoryImpl");

        String sql = "Select * from contact";

        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("Calling findById in ContactRepositoryImpl with id: {}", id);
        String sql = "Select * from contact where id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)
                )
        );
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Calling save in ContactRepositpryImpl with data: id:{}, fullName:{}",
                contact.getId(),
                (contact.getFirstName() + " " + contact.getLastName())
        );

        String sql = "INSERT INTO contact (first_name, last_name, email, phone) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(),
                contact.getEmail(), contact.getPhone());

        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("Call update in ContactRepositoryImpl with id: {}", contact.getId());

        Contact existedContact = findById(contact.getId()).orElse(null);

        if(existedContact != null) {
            String sql = "UPDATE contact SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(),
                    contact.getEmail(), contact.getPhone(), contact.getId());
            return contact;
        }

        log.warn("Contact with ID = {} not found!", contact.getId());

        throw new ContactNotFoundException("Contact for update not found! ID: " + contact.getId());
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Calling deleteById in ContactRepositoryImpl with ID: {}", id);

        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
