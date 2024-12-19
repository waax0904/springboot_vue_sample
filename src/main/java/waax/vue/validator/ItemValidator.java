package waax.vue.validator;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import waax.vue.service.UpdateItemCommand;

@Service
@Log4j2
public class ItemValidator extends CommonValidator {

	public void checkItemCommand(UpdateItemCommand command) {

		if (command == null) {
			log.error("UpdateItemCommand is null");
			throw new IllegalArgumentException();

		}

		checkItemId(command);
		checkItemName(command);

	}

	private void checkItemId(UpdateItemCommand command) {
		String id = command.getId();

		if (this.isNumber(id)) {
			return;

		} else {
			throw new IllegalArgumentException();

		}

	}

	private void checkItemName(UpdateItemCommand command) {
		String name = command.getName();

		String errMsg = this.checkText(name, 20);

		if (!this.isEmpty(errMsg)) {
			throw new IllegalArgumentException(errMsg);

		}

	}

}
