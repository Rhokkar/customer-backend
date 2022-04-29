package at.fhv.jazzers.customerBackend.infrastructure;

import at.fhv.jazzers.customerBackend.ServiceRegistry;
import at.fhv.jazzers.customerBackend.domain.model.Customer;
import at.fhv.jazzers.customerBackend.domain.model.CustomerId;
import at.fhv.jazzers.customerBackend.domain.repository.CustomerRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class HibernateCustomerRepository implements CustomerRepository {
    private final EntityManager entityManager = ServiceRegistry.entityManager();

    @Override
    public Optional<Customer> searchById(CustomerId customerId) {
        return entityManager
                .createQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId", Customer.class)
                .setParameter("customerId", customerId)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Customer> searchByName(String name) {
        return entityManager
                .createQuery("SELECT c FROM Customer c WHERE LOWER(c.givenName) LIKE LOWER(:name) OR LOWER(c.familyName) LIKE LOWER(:name)", Customer.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
}
