package at.fhv.jazzers.customerBackend;

import at.fhv.jazzers.customerBackend.communication.RMI_CustomerServiceImpl;
import at.fhv.jazzers.shared.api.RMI_CustomerService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {
    public static EntityManager entityManager;
    public static RMI_CustomerService rmi_customerService;

    public static EntityManager entityManager() {
        if (entityManager == null) {
            Map<String, Object> configOverrides = new HashMap<>();
            configOverrides.put("javax.persistence.jdbc.user", System.getenv("CUSTOMER_POSTGRES_USER"));
            configOverrides.put("javax.persistence.jdbc.password", System.getenv("CUSTOMER_POSTGRES_PASSWORD"));

            entityManager = Persistence.createEntityManagerFactory("CustomerBackend", configOverrides).createEntityManager();
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
