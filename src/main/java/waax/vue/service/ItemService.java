package waax.vue.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import waax.vue.entity.ItemEntity;
import waax.vue.model.ItemListApiModel;
import waax.vue.repository.ItemId;
import waax.vue.repository.ItemRepository;

@Service
@Builder
@Log4j2
public class ItemService {

	ItemRepository repository;

	ItemMapper mapper;

	public ItemListApiModel getItems() {
		log.info("Get all items from mariaDB");

		List<ItemEntity> items = repository.findAll();
		ItemListApiModel list = mapper.toList(items);

		log.info(list);

		return list;

	}

	@Transactional
	public void saveItem(String name) {
		ItemId seqItemId = ItemId.of(repository.getNextItemIdValue());
		UpdateItemCommand updateCommand = UpdateItemCommand.builder().id(seqItemId.stringValue()).name(name).build();

		log.info("Create new item named : " + name);

		repository.save(mapper.toItem(updateCommand));

	}

	@Transactional
	public void updateItem(UpdateItemCommand command) {
		ItemEntity updateItem = mapper.toItem(command);

		log.info("Update item : id : " + command.getId());

		repository.save(updateItem);

	}

	@Transactional
	public void deleteItem(int itemId) {
		log.info("Delete item id : " + itemId);

		try {
			ItemEntity entity = repository.findById(itemId).get();

			if (entity != null) {
				repository.deleteById(itemId);

			}

		} catch (NoSuchElementException e) {
			String errMsg = "The item has been delete. itemId:" + itemId;
			log.info(errMsg);
			throw new IllegalArgumentException(errMsg);

		}

	}

}
