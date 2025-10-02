package G50.pdp.server;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

@Component
public class DataSource {
private final EntityManagerFactory entityManagerFactory;
    public DataSource() {
        entityManagerFactory = Persistence.createEntityManagerFactory("employee_db");
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
