package at.fhv.jazzers.customerBackend;

import at.fhv.jazzers.customerBackend.communication.RMI_CustomerServiceImpl;
import at.fhv.jazzers.shared.api.RMI_CustomerService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.rmi.RemoteException;

public class ServiceRegistry {
    public static EntityManager entityManager;
    public static RMI_CustomerService rmi_customerService;

    public static EntityManager entityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("CustomerBackend").createEntityManager();
        }
        return entityManager;
    }

    public static RMI_CustomerService rmi_customerService() throws RemoteException {
        if (rmi_customerService == null) {
            rmi_customerService = new RMI_CustomerServiceImpl();
        }
        return rmi_customerService;
    }
}
