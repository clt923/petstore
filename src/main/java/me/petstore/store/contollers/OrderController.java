package me.petstore.store.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.petstore.store.models.Customer;
import me.petstore.store.models.Invoice;
import me.petstore.store.models.Order;
import me.petstore.store.models.Pet;
import me.petstore.store.repositories.CustomerRepository;
import me.petstore.store.repositories.InvoiceRepository;
import me.petstore.store.repositories.OrderRepository;

@RestController
public class OrderController {
    private OrderRepository orderRepository;
    private InvoiceRepository invoiceRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, InvoiceRepository invoiceRepository,
            CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
    }

    @RequestMapping(path = "/order/add/{petId}/{customerId}", method = RequestMethod.GET)
    public long add(@PathVariable("petId") final long petId, @PathVariable("customerId") final long customerId) {
        // Get pet info by pet id, by querying the Petstore Inventory app
        Pet pet = new Pet();
        pet.setId(petId);
        pet.setStatus("adopt");
        pet.setPrice(30);
        // Customer customer = customerRepository.getOne(customerId);
        // Customer customer = customerRepository.findById(customerId).get();
        Customer customer = customerRepository.findById(customerId).orElseThrow(IllegalArgumentException::new);
        Order order = new Order(new Pet(), customer);
        Invoice invoice = new Invoice(order.getPrice(), String.format("%s %s", pet.getStatus(), pet.getName()), order);
        order.setInvoice(invoice);
        orderRepository.save(order);
        return orderRepository.count();
    }
}
