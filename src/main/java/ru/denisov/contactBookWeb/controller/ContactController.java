package ru.denisov.contactBookWeb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.denisov.contactBookWeb.entity.Contact;
import ru.denisov.contactBookWeb.service.ContactService;


@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/")
    public String getContactBook(Model model){
        model.addAttribute("contacts", contactService.findAll());
        return "index";
    }

    @GetMapping("/contact/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contactPage";
    }

    @GetMapping("/contact/edit/{id}")
    public String showEditForm(Model model, @PathVariable Long id) {
        model.addAttribute("contact", contactService.findById(id));
        return "contactPage";
    }

    @PostMapping("/contact/contactPage")
    public String createOrReplace(@ModelAttribute Contact contact){
        contactService.createOrReplace(contact);

        return "redirect:/";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(Model model, @PathVariable Long id) {
        contactService.deleteById(id);

        return "redirect:/";
    }
}
