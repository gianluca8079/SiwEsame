package it.romaeur.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.romaeur.siw.model.VotoMvp;
import it.romaeur.siw.service.UserService;
import it.romaeur.siw.service.VotoMvpService;

	@Component
	public class VotoMvpValidator implements Validator {

		@Autowired UserService userService;
		@Autowired VotoMvpService votoMvpService;

		
		@Override
		public boolean supports(Class<?> clazz) {
			return VotoMvp.class.equals(clazz);
		}

		@Override
		public void validate(Object o, Errors errors) {
			VotoMvp voto=(VotoMvp)o;
			if(this.votoMvpService.alreadyExists(voto))
				errors.reject("votoMvp.duplicate");
		}


}
