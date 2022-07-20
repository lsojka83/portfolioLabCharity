package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.Donation;

import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    public Optional<Integer> getTotalQuantity();


    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "DELETE FROM `charity-donation`.user_donations WHERE donations_id = ?1")
    void deleteFromParentRelationTableById(Long id);

}
