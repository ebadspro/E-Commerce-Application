package segments.info.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "UserInfo")
public class UserInfo {

    @Id
    @Column(name = "user_id")
    public String id;

    public String username;

    public String password;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )



    private Set <UserRole> roles = new HashSet<>();


}
