package waax.vue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import waax.vue.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

	@Query(value = "select next VALUE for item_id", nativeQuery = true)
	public int getNextItemIdValue();

}
