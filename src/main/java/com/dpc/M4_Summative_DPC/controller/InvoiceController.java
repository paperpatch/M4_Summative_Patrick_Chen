package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.models.Game;
import com.dpc.M4_Summative_DPC.models.Invoice;
import com.dpc.M4_Summative_DPC.repository.InvoiceRepository;
import com.dpc.M4_Summative_DPC.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    ServiceLayer service;

    // Get all invoice
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return service.getAllInvoices();
    }

    // Get invoice by id
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable int id) {
        Optional<Invoice> invoice = service.getInvoiceById(id);
        if (!invoice.isPresent()) {
            throw new IllegalArgumentException("Invalid id, enter the correct id.");
        }
        return invoice.get();
    }

    // Create Invoice
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return service.addInvoice(invoice);
    }

    // Update Invoice
    @RequestMapping(value = "invoice/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
        if (invoice.getInvoiceId() == null) {
            invoice.setInvoiceId(id);
        } else if (invoice.getInvoiceId() != id) {

            throw new IllegalArgumentException("Invalid id, enter the correct id.");
        }
        service.updateInvoice(invoice);
    }
    @PutMapping("/invoice")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice2(@RequestBody Invoice invoice) {
        service.updateInvoice(invoice);
    }

    //    Delete invoice
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroyInvoice(@PathVariable int id) {
        service.deleteInvoice(id);
    }

}
