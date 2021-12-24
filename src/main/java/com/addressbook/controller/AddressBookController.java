package com.addressbook.controller;

import com.addressbook.dto.ContactDTO;
import com.addressbook.dto.ResponseDTO;
import com.addressbook.model.Contact;
import com.addressbook.service.IAddressBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class AddressBookController {
    @Autowired
    private IAddressBookService addressbookservice;

    @Operation(summary = "get person", description = "get a person ist ", tags = "Get")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "found the person present in list",
            content = {@Content(mediaType = "application/json",schema = @Schema(implementation = ContactDTO.class))}),
            @ApiResponse(responseCode = "404", description = "No person found", content = @Content)

    })
    @GetMapping("/get" )
    public ResponseEntity<ResponseDTO> getContactData() {
        List<Contact> contactList = null;
        contactList=addressbookservice.getContact();
        ResponseDTO response = new ResponseDTO("Get call success", contactList);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{contactId}")
    public ResponseEntity<ResponseDTO> getContactData(@PathVariable("contactId") int contactId) {
        Contact contact = addressbookservice.getContactById(contactId);
        ResponseDTO response = new ResponseDTO("Get call success for id", contact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addContactData(@RequestBody ContactDTO contactDTO) {
        Contact contact = addressbookservice.createContact(contactDTO);
        ResponseDTO response = new ResponseDTO("Created contact data for", contact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    @PutMapping("/update/{contactId}")
    public ResponseEntity<ResponseDTO> updateContactData(@PathVariable("contactId") int contactId,
                                                         @RequestBody ContactDTO contactDTO) {
        Contact contact = null;
        contact=addressbookservice.updateContact(contactId, contactDTO);
        ResponseDTO response = new ResponseDTO("Updated contact data for", contact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

}