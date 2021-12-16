package com.addressbook.controller;

import com.addressbook.dto.ContactDTO;
import com.addressbook.dto.ResponseDTO;
import com.addressbook.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressbookservice")
public class AddressBookController {

    @RequestMapping(value = { "", "/", "/get" })
    public ResponseEntity<ResponseDTO> getContactData() {
/**
 * localhost:8080/addressbookservice/get
 * localhost:8080/addressbookservice/get/2
 * {
 *     "message": "Get call success",
 *     "data": {
 *         "contactId": 1,
 *         "firstName": "Ashish",
 *         "lastName": "Sahu",
 *         "address": "Bhubneswar",
 *         "state": "Odisha",
 *         "city": "Bhubneswar",
 *         "zip": "768003",
 *         "phone": "9437390433"
 *     }
 * }
 */
        Contact contact = new Contact(1,
                new ContactDTO("Ashish", "Sahu", "Bhubneswar", "Odisha", "Bhubneswar", "768003", "9437390433"));
        ResponseDTO response = new ResponseDTO("Get call success", contact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{contactId}")
    public ResponseEntity<ResponseDTO> getContactData(@PathVariable("contactId") int contactId) {
        Contact contact = new Contact(2,
                new ContactDTO("Naval", "Kumar", "Solapur", "Maharastra", "Solapur", "561210", "9999684337"));
        ResponseDTO response = new ResponseDTO("Get call success for id", contact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addContactData(@RequestBody ContactDTO contactDTO) {
        Contact contact = new Contact(1, contactDTO);
        ResponseDTO response = new ResponseDTO("Created contact data for", contact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    @PutMapping("/update/{contactId}")
    public ResponseEntity<ResponseDTO> updateContactData(@PathVariable("contactId") int contactId,
                                                         @RequestBody ContactDTO contactDTO) {
        Contact contact = new Contact(1, contactDTO);
        ResponseDTO response = new ResponseDTO("Updated contact data for", contact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{contactId}")
    public ResponseEntity<ResponseDTO> deleteContactData(@PathVariable("contactId") int contactId) {
        ResponseDTO response = new ResponseDTO("Delete call success for id ", "deleted id:" + contactId);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }
}