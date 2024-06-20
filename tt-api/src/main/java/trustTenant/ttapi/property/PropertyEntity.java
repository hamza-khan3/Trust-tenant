package trustTenant.ttapi.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import trustTenant.ttapi.listing.ListingEntity;
import trustTenant.ttapi.security.UserEntity;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = { "listings", "owner" })
@Entity
@Table(name = "PROPERTY", schema = "TRUST_TENANT")
public class PropertyEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PropertyId")
    private long propertyId;

    @Column(name = "Address")
    private String address;

    @Column(name = "Type")
    private String type;

    //@Column(name = "OwnerId")
    //private long ownerId;

    @Column(name = "CommunityId")
    private long communityId;

    @OneToMany(mappedBy = "property")
    private List<ListingEntity> listings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "OwnerId")
    private UserEntity owner;

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }*/

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(long communityId) {
        this.communityId = communityId;
    }

    public List<ListingEntity> getListings() {
        return listings;
    }

    public void setListings(List<ListingEntity> listings) {
        this.listings = listings;
    }
}
