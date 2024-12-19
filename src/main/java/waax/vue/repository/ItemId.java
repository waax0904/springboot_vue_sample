package waax.vue.repository;

import java.io.Serializable;

import lombok.Value;

@Value(staticConstructor = "of")
public class ItemId implements Serializable {

	private static final long serialVersionUID = 8602182816641544451L;

	private int id;

	public int value() {
		return id;

	}

	public String stringValue() {
		return String.valueOf(id);

	}
}