package trustTenant.ttapi.property;

import trustTenant.ttapi.listing.ListingDescEntity;
import trustTenant.ttapi.listing.ListingEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchFilter implements Serializable {

    private String type;
    private int rentMin;
    private int rentMax;
    private int minBedrooms;
    private int minBathrooms;
    private List<String> descriptors = new ArrayList<>();

    public boolean filterListing(ListingEntity listing) {
        if(!listing.getStatus().equals("ACTIVE")) return false;

        if(rentMax > 0 && listing.getRent() > rentMax) return false;

        if(rentMin > 0 && listing.getRent() < rentMin) return false;

        if(minBedrooms > 0 && listing.getNumBeds() < minBedrooms) return false;

        if(minBathrooms > 0 && listing.getNumBaths() < minBathrooms) return false;

        if(descriptors.isEmpty()) return true;

        List<String> listingDescriptors = listing.getDescriptors();

        boolean found;
        for(String desc : descriptors) {
            found = false;
            for(String listingDesc : listingDescriptors) {
                if(listingDesc.equals(desc)) {
                    found = true;
                    break;
                }
            }
            if(!found) return false;
        }
        return true;
    }

    public String getType() {
        return type;
    }

    public int getRentMin() {
        return rentMin;
    }

    public int getRentMax() {
        return rentMax;
    }

    public int getMinBedrooms() {
        return minBedrooms;
    }

    public int getMinBathrooms() {
        return minBathrooms;
    }

    public List<String> getDescriptors() {
        return descriptors;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRentMin(int rentMin) {
        this.rentMin = rentMin;
    }

    public void setRentMax(int rentMax) {
        this.rentMax = rentMax;
    }

    public void setMinBedrooms(int minBedrooms) {
        this.minBedrooms = minBedrooms;
    }

    public void setMinBathrooms(int minBathrooms) {
        this.minBathrooms = minBathrooms;
    }

    public void setDescriptors(List<String> descriptors) {
        this.descriptors = descriptors;
    }
}
