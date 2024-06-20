package trustTenant.ttapi.listing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListingController {

    @Autowired
    private ListingService listingService;

    @RequestMapping("/properties/{id}/listings")
    public List<Listing> getAllTopics(@PathVariable int id) {
        List<Listing> listings = listingService.getPropertyListings(id);
        if(listings.isEmpty()) throw new ErrorResponseException(HttpStatusCode.valueOf(404));
        return listings;
    }
}
