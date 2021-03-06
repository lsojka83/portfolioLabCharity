package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Status;

public interface StatusRepository extends JpaRepository<Status,Long> {

    Status findByValue(String value);


}
