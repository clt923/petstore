package me.petstore.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.petstore.store.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
