package it.romaeur.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.romaeur.siw.model.Partita;
import it.romaeur.siw.model.VotoMvp;
import it.romaeur.siw.service.UserService;


@Component
public class DuplicateVotoValidator implements Validator {
	
	@Autowired UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return VotoMvp.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Partita partita=(Partita)o;
		if(this.userService.controllaVotiDoppi(partita))
			errors.reject("voto.duplicate");		
	}
}
