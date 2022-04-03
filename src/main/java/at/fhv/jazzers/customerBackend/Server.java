package at.fhv.jazzers.customerBackend;

import at.fhv.jazzers.customerBackend.communication.RMI_Server;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    public static void main(String[] args) throws IOException {
        // (Data 1/2) Prepare data from data.sql
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.sql");
        String[] lines = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).split("\n");
        AtomicInteger i = new AtomicInteger(1);

        // (Data 2/2) Insert data into database
        EntityManager entityManager = ServiceRegistry.entityManager();
        entityManager.getTransaction().begin();
        Arrays.stream(lines).forEach(line -> entityManager.createNativeQuery(line).setParameter("customerIdInternal", i.getAndIncrement()).setParameter("id", UUID.randomUUID()).executeUpdate());
        entityManager.getTransaction().commit();

        RMI_Server.start();
    }
}
