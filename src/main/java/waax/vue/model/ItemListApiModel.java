package waax.vue.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Builder
@Value
@EqualsAndHashCode(callSuper = true)
public class ItemListApiModel extends RepresentationModel<ItemListApiModel> implements Serializable {

	private static final long serialVersionUID = -5479781877675166081L;

	List<ItemApiModel> items;

	@Builder
	@Value
	@EqualsAndHashCode(callSuper = true)
	public static class ItemApiModel extends RepresentationModel<ItemApiModel> implements Serializable {

		private static final long serialVersionUID = 230454176430928036L;

		private String id;

		private String name;

	}

}
