package trustTenant.ttapi.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PropertyViewController {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping("/properties/property/{id}/view")
    public String getProperty(
            @PathVariable int id,
            Model model) {
        PropertyEntity property = propertyService.getProperty(id);
        if(property == null) throw new ErrorResponseException(HttpStatusCode.valueOf(404));
        model.addAttribute("property", property);
        return "property";
    }
}
