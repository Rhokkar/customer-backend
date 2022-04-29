package at.fhv.jazzers.customerBackend.domain.repository;

import at.fhv.jazzers.customerBackend.domain.model.Customer;
import at.fhv.jazzers.customerBackend.domain.model.CustomerId;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> searchById(CustomerId customerId);
    List<Customer> searchByName(String name);
}
