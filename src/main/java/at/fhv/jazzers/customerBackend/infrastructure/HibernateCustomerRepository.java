package at.fhv.jazzers.customerBackend.infrastructure;

import at.fhv.jazzers.customerBackend.domain.repository.CustomerRepository;

public class HibernateCustomerRepository implements CustomerRepository {
    // SELECT * FROM customer WHERE firstName = name OR lastName = name;
}
