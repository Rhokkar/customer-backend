package at.fhv.jazzers.customerBackend.communication;

import at.fhv.jazzers.customerBackend.ServiceRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMI_Server {
    public static void start() throws RemoteException, MalformedURLException {
        // Create Registry
        LocateRegistry.createRegistry(Integer.parseInt(System.getenv("CUSTOMER_RMI_PORT")));

        // Remote Objects try to listen to docker ip, but they need to listen to localhost (local .env) or application-server-ip (azure .env)
        System.setProperty("java.rmi.server.hostname", System.getenv("CUSTOMER_RMI_HOST"));

        // Bind Services
        Naming.rebind("rmi://localhost/customerService", ServiceRegistry.rmi_customerService());
        System.out.println("Customer Service bound on PORT " + System.getenv("CUSTOMER_RMI_PORT"));
    }
}
