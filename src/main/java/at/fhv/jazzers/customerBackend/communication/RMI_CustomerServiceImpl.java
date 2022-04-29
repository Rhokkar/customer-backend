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
        super(Integer.parseInt(System.getenv("CUSTOMER_RMI_PORT")));
    }

    @Override
    public Optional<CustomerDetailDTO> searchById(UUID customerId) throws RemoteException {
        Optional<Customer> optionalCustomer = customerRepository.searchById(new CustomerId(customerId));
        Optional<CustomerDetailDTO> optionalCustomerDetailDTO = Optional.empty();

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            AddressDTO addressDTO = new AddressDTO(customer.addressaddressCountry(), customer.addressaddressLocality(), customer.addresspostalCode(), customer.addressstreetAddress(), customer.addresshouseNumber());
            BankAccountDTO bankAccountDTO = new BankAccountDTO(customer.bankAccountbankcity(), customer.bankAccountbankbankCode(), customer.bankAccountbankbic(), customer.bankAccountiban());
            CreditCardDTO creditCardDTO = new CreditCardDTO(customer.creditCardnumber(), customer.creditCardtype());
            CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO(customer.customerId().id(), customer.givenName(), customer.familyName(), customer.gender(), customer.birthDate(), customer.email(), customer.phoneNo(), customer.mobileNo(), addressDTO, bankAccountDTO, creditCardDTO);

            optionalCustomerDetailDTO = Optional.of(customerDetailDTO);
        }

        return optionalCustomerDetailDTO;
    }

    @Override
    public List<CustomerOverviewDTO> searchByName(String name) throws RemoteException {
        return customerRepository.searchByName(name).stream().map(customer -> new CustomerOverviewDTO(customer.customerId().id(), customer.givenName(), customer.familyName())).collect(Collectors.toList());
    }
}
