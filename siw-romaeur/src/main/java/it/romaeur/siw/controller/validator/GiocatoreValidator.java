package it.romaeur.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.romaeur.siw.model.Giocatore;
import it.romaeur.siw.service.GiocatoreService;

@Component
public class GiocatoreValidator implements Validator {

	@Autowired GiocatoreService giocatoreService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Giocatore.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Giocatore giocatore=(Giocatore)target;
		if(this.giocatoreService.alreadyExists(giocatore))
			errors.reject("giocatore.duplicate");
	}

}
