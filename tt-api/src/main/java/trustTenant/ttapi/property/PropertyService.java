package trustTenant.ttapi.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import trustTenant.ttapi.listing.ListingEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<PropertyEntity> getProperties() {
        return propertyRepository.findAll();
    }

    public List<PropertyEntity> filterAll(SearchFilter filter) {
        List<PropertyEntity> results = new ArrayList<>();

        String type = filter.getType();
        List<PropertyEntity> preFiltered;
        if(type == null || type.isEmpty()) {
            preFiltered = propertyRepository.findAll();
        } else {
            preFiltered = propertyRepository.findByType(type);
        }

        for(PropertyEntity property : preFiltered) {
            for(ListingEntity listing : property.getListings()) {
                if(filter.filterListing(listing)) {
                    results.add(property);
                    break;
                }
            }
        }

        return results;
    }

    public PropertyEntity getProperty(int id) {
        return propertyRepository.findByPropertyId(id);
    }

    public void addProperty(PropertyEntity property) {
        if(propertyRepository.findByAddress(property.getAddress()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        propertyRepository.save(property);
    }

    public void updateProperty(PropertyEntity property) {
        if(propertyRepository.findByPropertyId(property.getPropertyId()) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        propertyRepository.save(property);
    }

    public void deleteProperty(long id) {
        propertyRepository.deleteById(id);
    }
}
