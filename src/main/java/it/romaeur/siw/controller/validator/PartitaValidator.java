package it.romaeur.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.romaeur.siw.model.Partita;
import it.romaeur.siw.service.PartitaService;

@Component
public class PartitaValidator implements Validator {

	@Autowired PartitaService partitaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Partita.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Partita partita=(Partita)target;
		if(this.partitaService.alreadyExists(partita))
			errors.reject("partita.duplicate");
	}

}
