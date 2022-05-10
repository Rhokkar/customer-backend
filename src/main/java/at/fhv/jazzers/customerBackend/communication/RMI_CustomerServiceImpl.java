package at.fhv.jazzers.customerBackend.communication;

import at.fhv.jazzers.customerBackend.domain.model.Customer;
import at.fhv.jazzers.customerBackend.domain.model.CustomerId;
import at.fhv.jazzers.customerBackend.domain.repository.CustomerRepository;
import at.fhv.jazzers.customerBackend.infrastructure.HibernateCustomerRepository;
import at.fhv.jazzers.shared.api.RMI_CustomerService;
import at.fhv.jazzers.shared.dto.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class RMI_CustomerServiceImpl extends UnicastRemoteObject implements RMI_CustomerService {
    private final CustomerRepository customerRepository = new HibernateCustomerRepository();

    public RMI_CustomerServiceImpl() throws RemoteException {
        super(1100);
    }

    @Override
    public CustomerDetailDTO searchById(UUID customerId) throws RemoteException {
        Optional<Customer> customer = customerRepository.searchById(new CustomerId(customerId));

        if (customer.isEmpty()) {
            throw new IllegalArgumentException("The customer does not exist.");
        }

        AddressDTO addressDTO = new AddressDTO(customer.get().addressaddressCountry(), customer.get().addressaddressLocality(), customer.get().addresspostalCode(), customer.get().addressstreetAddress(), customer.get().addresshouseNumber());
        BankAccountDTO bankAccountDTO = new BankAccountDTO(customer.get().bankAccountbankcity(), customer.get().bankAccountbankbankCode(), customer.get().bankAccountbankbic(), customer.get().bankAccountiban());
        CreditCardDTO creditCardDTO = new CreditCardDTO(customer.get().creditCardnumber(), customer.get().creditCardtype());

        return new CustomerDetailDTO(customer.get().customerId().id(), customer.get().givenName(), customer.get().familyName(), customer.get().gender(), customer.get().birthDate(), customer.get().email(), customer.get().phoneNo(), customer.get().mobileNo(), addressDTO, bankAccountDTO, creditCardDTO);
    }

    @Override
    public List<CustomerOverviewDTO> searchByName(String name) throws RemoteException {
        return customerRepository.searchByName(name).stream().map(customer -> new CustomerOverviewDTO(customer.customerId().id(), customer.givenName(), customer.familyName())).collect(Collectors.toList());
    }
}
