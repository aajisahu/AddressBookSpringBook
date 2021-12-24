package com.addressbook.service;

import com.addressbook.dto.ContactDTO;
import com.addressbook.model.Contact;
import com.addressbook.repository.ContactRepository;
import com.addressbook.util.MailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class AddressBookService implements IAddressBookService {
    private List<Contact> contactList = new ArrayList<>();

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    private MailService mailService;

    @Override
    public List<Contact> getContact() {

        return contactList;
    }

    @Override
    public Contact getContactById(int contactId) {
        return contactList.get(contactId - 1);
    }

    @Override
    public Contact createContact(ContactDTO contactDTO) {
        Contact contact=null;
        Contact contactData =new Contact();
        contactData.setContactId(contactDTO.getContactId());
        contactData.setFirstName(contactDTO.getFirstName());
        contactData.setLastName(contactDTO.getLastName());
        contactData.setAddress(contactDTO.getAddress());
        contactData.setState(contactDTO.getState());
        contactData.setCity(contactDTO.getCity());
        contactData.setZip(contactDTO.getZip());
        contactData.setPhone(contactDTO.getPhone());
        contactData.setEmail(contactDTO.getEmail());
        contactList.add(contactData);
        contactRepository.save(contactData);
        try {
            mailService.sendNotification(contactData);
        }
        catch (MailException e){
            e.printStackTrace();
        }
        return contactData;
    }

    @Override

    public Contact updateContact(int contactId, ContactDTO contactDTO) {
        Contact contact = this.getContactById(contactId);
        if (!contactList.isEmpty()){
            contact.setFirstName(contactDTO.firstName);
            contact.setLastName(contactDTO.lastName);
            contact.setAddress(contactDTO.address);
            contact.setState(contactDTO.state);
            contact.setCity(contactDTO.city);
            contact.setZip(contactDTO.zip);
            contact.setPhone(contactDTO.phone);
            contactList.set(contactId - 1, contact);
            return contact;
        }
        else {
            return contact=null;
        }
    }
}