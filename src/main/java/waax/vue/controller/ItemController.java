package waax.vue.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.Builder;
import waax.vue.model.ItemListApiModel;
import waax.vue.service.ItemService;
import waax.vue.service.UpdateItemCommand;
import waax.vue.validator.ItemValidator;

@RestController
@RequestMapping(value = "/items")
@CrossOrigin(origins = {"http://localhost:5500"})
@Builder
public class ItemController {

	private final ItemService service;

	private final ItemValidator validate;

	@GetMapping
	@Operation(summary = "Get all items", description = "Get all items.")
	public ItemListApiModel findAll() {
		return service.getItems();

	}

	@PostMapping
	@Operation(summary = "Add a item", description = "Add a item.")
	public void addOne(HttpServletResponse response, @RequestBody UpdateItemCommand command) throws IOException {
		validate.checkItemCommand(command);
		service.saveItem(command.getName());

	}

	@PutMapping
	@Operation(summary = "Update a item name", description = "Update a item name")
	public void updateOne(HttpServletResponse response, @RequestBody UpdateItemCommand command) throws IOException {
		validate.checkItemCommand(command);		
		service.updateItem(command);

	}

	@DeleteMapping(value = "{item_id}")
	@Operation(summary = "Delete by item id", description = "Delete by item id.")
	public void deleteOne(HttpServletResponse response, @PathVariable("item_id") int itemId) throws IOException {
		service.deleteItem(itemId);

	}

}