package at.fhv.jazzers.customerBackend.communication;

import at.fhv.jazzers.customerBackend.domain.repository.CustomerRepository;
import at.fhv.jazzers.customerBackend.infrastructure.HibernateCustomerRepository;
import at.fhv.jazzers.shared.api.RMI_CustomerService;
import at.fhv.jazzers.shared.dto.CustomerOverviewDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMI_CustomerServiceImpl extends UnicastRemoteObject implements RMI_CustomerService {
    private final CustomerRepository customerRepository = new HibernateCustomerRepository();

    public RMI_CustomerServiceImpl() throws RemoteException {
        super(Integer.parseInt(System.getenv("CUSTOMER_RMI_PORT")));
    }

    @Override
    public List<CustomerOverviewDTO> search(String name) throws RemoteException {
        return List.of();
        // return customerRepository.search(name);
    }
}
