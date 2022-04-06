package at.fhv.jazzers.customerBackend.communication;

import at.fhv.jazzers.customerBackend.ServiceRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMI_Server {
    public static void start() throws RemoteException, MalformedURLException {
        // Remote Objects try to listen to docker ip, but they need to listen to localhost (local .env) or application-server-ip (azure .env)
        System.setProperty("java.rmi.server.hostname", System.getenv("CUSTOMER_RMI_HOST"));
        System.setProperty("com.sun.management.jmxremote.port", System.getenv("CUSTOMER_RMI_PORT"));
        System.setProperty("com.sun.management.jmxremote.rmi.port", System.getenv("CUSTOMER_RMI_PORT"));
        // System.setProperty("com.sun.management.jmxremote", "true");
        // System.setProperty("com.sun.management.jmxremote.local.only", "false");
        System.setProperty("com.sun.management.jmxremote.authenticate", "false");
        System.setProperty("com.sun.management.jmxremote.ssl", "false");

        // Create Registry
        LocateRegistry.createRegistry(Integer.parseInt(System.getenv("CUSTOMER_RMI_PORT")));

        // Bind Services
        Naming.rebind("rmi://localhost:1100/customerService", ServiceRegistry.rmi_customerService());
        System.out.println("Customer Service bound on PORT " + System.getenv("CUSTOMER_RMI_PORT"));
    }
}
