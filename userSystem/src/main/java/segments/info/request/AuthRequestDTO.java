package segments.info.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class AuthRequestDTO {

    private String username;

    private String password;
//
//    private Set <UserInfo> roles = new HashSet<>();

}
