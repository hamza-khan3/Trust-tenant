package trustTenant.ttapi.listing;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ListingService {

    private final List<Listing> defaultListings = Arrays.asList(
           new Listing(1, 1, 1, 1, "Studio Apartment", 400),
           new Listing(1, 2, 2, 1, "2 Bed 1 Bath Apartment", 700),
           new Listing(2, 1, 1, 1, "1 Bedroom Basement Suite", 800),
           new Listing(3, 1, 3, 2.5f, "3 Bedroom Townhouse", 1600)
    );

    public List<Listing> getPropertyListings(int id) {
        return defaultListings.stream().filter(l -> l.getPropertyId() == id).toList();
    }
}
