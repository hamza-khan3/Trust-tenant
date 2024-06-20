package trustTenant.ttapi.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import trustTenant.ttapi.listing.ListingEntity;
import trustTenant.ttapi.property.PropertyEntity;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = { "properties" })
@Entity
@Table(name = "USER", schema = "TRUST_TENANT")
public class UserEntity {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UserId")
    private long userId;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "FName")
    private String fName;

    @Column(name = "LName")
    private String lName;

    @Column(name = "Role")
    private String role;

    @OneToMany(mappedBy = "owner")
    private List<PropertyEntity> properties = new ArrayList<>();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
