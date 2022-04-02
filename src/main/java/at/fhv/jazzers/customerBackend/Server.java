package at.fhv.jazzers.customerBackend;

import at.fhv.jazzers.customerBackend.communication.RMI_Server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) throws MalformedURLException, RemoteException {
        RMI_Server.start();
    }
}
