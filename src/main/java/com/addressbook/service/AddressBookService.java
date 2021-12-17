package com.addressbook.service;

import com.addressbook.dto.ContactDTO;
import com.addressbook.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
    public class AddressBookService implements IAddressBookService {
        @Override
        public List<Contact> getContact() {
            List<Contact> contactList = new ArrayList<>();
            contactList.add(new Contact(1,
                    new ContactDTO("Ashish", "Sahu", "Bolangir", "Odisha", "Bhubneswar", "560064", "98765433540")));
            return contactList;
        }

        @Override
        public Contact getContactById(int contactId) {
            Contact contact = new Contact(2,
                    new ContactDTO("Naval", "Kumar", "Kolapur", "Maharastara", "Kolapur", "561240", "99991346790"));
            return contact;
        }

        @Override
        public Contact createContact(ContactDTO contactDTO) {
            Contact contact = new Contact(1, contactDTO);
            return contact;
        }

        @Override
        public Contact updateContact(int contactId, ContactDTO contactDTO) {
            Contact contact = new Contact(1, contactDTO);
            return contact;
        }

        @Override
        public void deleteContact(int contactId) {

        }
    }


