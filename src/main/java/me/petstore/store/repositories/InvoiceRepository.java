package me.petstore.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.petstore.store.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
