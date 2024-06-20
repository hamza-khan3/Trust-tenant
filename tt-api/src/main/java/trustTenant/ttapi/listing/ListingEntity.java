package trustTenant.ttapi.listing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import trustTenant.ttapi.property.PropertyEntity;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = { "property", "descriptors" })
@Entity
@Table(name = "LISTING", schema = "TRUST_TENANT")
public class ListingEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ListingNum")
    private long listingNum;

    public long getListingNum() {
        return listingNum;
    }

    public void setListingNum(long listingNum) {
        this.listingNum = listingNum;
    }

    @Id
    @Column(name = "PropertyId")
    private long propertyId;

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    @Column(name = "Type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "NumBeds")
    private int numBeds;

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    @Column(name = "NumBaths")
    private int numBaths;

    public int getNumBaths() {
        return numBaths;
    }

    public void setNumBaths(int numBaths) {
        this.numBaths = numBaths;
    }

    @Column(name = "SqrFootage")
    private int sqrFootage;

    public int getSqrFootage() {
        return sqrFootage;
    }

    public void setSqrFootage(int sqrFootage) {
        this.sqrFootage = sqrFootage;
    }

    @Column(name = "Rent")
    private int rent;

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    @Column(name = "Status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "PropertyId")
    private PropertyEntity property;

    public PropertyEntity getProperty() {
        return property;
    }

    public void setProperty(PropertyEntity property) {
        this.property = property;
    }

    @OneToMany(mappedBy = "listing")
    private List<ListingDescEntity> descriptors = new ArrayList<>();

    public List<String> getDescriptors() {
        ArrayList<String> descStrings = new ArrayList<>();
        for(ListingDescEntity desc : descriptors) {
            descStrings.add(desc.getDescriptor());
        }
        return descStrings;
    }

    public void setDescriptors(List<ListingDescEntity> descriptors) {
        this.descriptors = descriptors;
    }
}
