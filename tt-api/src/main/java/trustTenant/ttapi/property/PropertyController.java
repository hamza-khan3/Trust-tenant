package trustTenant.ttapi.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping("/properties")
    public List<PropertyEntity> getProperties() {
        return propertyService.getProperties();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/properties/filter")
    public List<PropertyEntity> getPropertiesFiltered(
            @RequestBody SearchFilter filter) {
        return propertyService.filterAll(filter);
    }

    @RequestMapping("/properties/property/{id}/json")
    public PropertyEntity getProperty(
            @PathVariable int id) {
        PropertyEntity property = propertyService.getProperty(id);
        if(property == null) throw new ErrorResponseException(HttpStatusCode.valueOf(404));
        return property;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/properties")
    public void addProperty(
            @RequestBody PropertyEntity property) {
        propertyService.addProperty(property);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/properties/property/{id}")
    public void updateProperty(
            @RequestBody PropertyEntity property,
            @PathVariable int id) {
        property.setPropertyId(id);
        propertyService.updateProperty(property);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/properties/property/{id}")
    public void deleteProperty(
            @PathVariable int id) {
        propertyService.deleteProperty(id);
    }
}


