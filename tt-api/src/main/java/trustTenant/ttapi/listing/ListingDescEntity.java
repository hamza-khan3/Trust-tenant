package trustTenant.ttapi.listing;

import jakarta.persistence.*;

@Entity
@Table(name = "LISTING_DESC", schema = "TRUST_TENANT")
public class ListingDescEntity {

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

    @Id
    @Column(name = "Descriptor")
    private String descriptor;

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PropertyId"),
            @JoinColumn(name = "ListingNum")
    })
    private ListingEntity listing;

    public ListingEntity getListing() {
        return listing;
    }

    public void setListing(ListingEntity listing) {
        this.listing = listing;
    }
}
