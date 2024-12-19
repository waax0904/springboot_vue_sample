package waax.vue.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import waax.vue.entity.ItemEntity;
import waax.vue.model.ItemListApiModel;
import waax.vue.model.ItemListApiModel.ItemApiModel;

@Service
public class ItemMapper {
	public ItemListApiModel toList(List<ItemEntity> items) {
		List<ItemApiModel> itemList = items.stream().map(v -> toItemModel(v)).collect(Collectors.toUnmodifiableList());
		return ItemListApiModel.builder().items(itemList).build();

	}

	public ItemApiModel toItemModel(ItemEntity item) {
		return ItemApiModel.builder().id(Integer.toString(item.getId())).name(item.getName()).build();

	}

	public ItemEntity toItem(UpdateItemCommand command) {
		ItemEntity newItem = new ItemEntity();
		newItem.setId(Integer.parseInt(command.getId()));
		newItem.setName(command.getName());

		return newItem;

	}
}