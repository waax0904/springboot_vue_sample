package waax.vue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket swaggerSetting() {
		return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("waax.vue")).paths(PathSelectors.any()).build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Springboot + Vue.js sample")
				.description("SpringBoot and Vue.js , let's goooooooooooo").version("ver.0-12345").build();

	}
}
