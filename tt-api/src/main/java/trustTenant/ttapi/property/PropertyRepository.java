package trustTenant.ttapi.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
    List<PropertyEntity> findByType(String type);

    PropertyEntity findByPropertyId(long id);

    PropertyEntity findByAddress(String address);
}
