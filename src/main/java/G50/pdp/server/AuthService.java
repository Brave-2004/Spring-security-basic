package G50.pdp.server;

import G50.pdp.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final DataSource dataSource;

    @Override
    public User loadUserByUsername(String username) {
        EntityManager entityManager = dataSource.getEntityManager();
        User user = entityManager.createQuery("from users where username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        return user;
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123"));
    }

}
