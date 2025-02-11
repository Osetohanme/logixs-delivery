package ng.com.nokt.demodelivery.repository;

import ng.com.nokt.demodelivery.entites.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
