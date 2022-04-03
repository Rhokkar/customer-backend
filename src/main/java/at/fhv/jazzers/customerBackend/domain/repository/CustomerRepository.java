package at.fhv.jazzers.customerBackend.domain.repository;

import at.fhv.jazzers.customerBackend.domain.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> search(String name);
}
