package ru.denisov.contactBookWeb.exceptions;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String s) {
        super(s);
    }
}
