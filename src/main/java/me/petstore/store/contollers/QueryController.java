package me.petstore.store.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.petstore.store.models.Customer;
import me.petstore.store.models.Invoice;
import me.petstore.store.models.Order;
import me.petstore.store.repositories.CustomerRepository;
import me.petstore.store.repositories.InvoiceRepository;
import me.petstore.store.repositories.OrderRepository;

@RestController
public class QueryController {
    private OrderRepository orderRepository;
    private InvoiceRepository invoiceRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public QueryController(OrderRepository orderRepository, InvoiceRepository invoiceRepository,
            CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
    }

    @RequestMapping(path = "/orders/{price}", method = RequestMethod.GET)
    public List<Order> orders(@PathVariable("price") final double price) {
        return orderRepository.getOrdersAbove25(price);
    }

    @RequestMapping(path = "/invoices", method = RequestMethod.GET)
    public List<Invoice> invoices() {
        return invoiceRepository.findAll();
    }

    @RequestMapping(path = "/customers", method = RequestMethod.GET)
    public List<Customer> customers() {
        return customerRepository.findAll();
    }
}
