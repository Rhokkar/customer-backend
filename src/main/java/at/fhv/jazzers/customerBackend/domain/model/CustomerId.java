package at.fhv.jazzers.customerBackend.domain.model;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CustomerId {
    private UUID id;

    protected CustomerId() {

    }

    public CustomerId(UUID id) {
        this.id = id;
    }

    public UUID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerId that = (CustomerId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}