package G50.pdp.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Permission;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private List<PermissionEnum>  permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role.name());
        return List.of();
    }


}
