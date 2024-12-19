package waax.vue.service;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateItemCommand implements Serializable {
	private static final long serialVersionUID = 2449897226655441225L;

	private String id;

	private String name;
}
