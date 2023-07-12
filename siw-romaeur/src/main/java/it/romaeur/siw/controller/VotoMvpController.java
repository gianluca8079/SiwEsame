package it.romaeur.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.romaeur.siw.controller.validator.DuplicateVotoValidator;
import it.romaeur.siw.controller.validator.PartitaValidator;
import it.romaeur.siw.model.Giocatore;
import it.romaeur.siw.model.Partita;
import it.romaeur.siw.model.User;
import it.romaeur.siw.model.VotoMvp;
import it.romaeur.siw.repository.VotoMvpRepository;
import it.romaeur.siw.service.GiocatoreService;
import it.romaeur.siw.service.PartitaService;
import it.romaeur.siw.service.PrestazioneService;
import it.romaeur.siw.service.UserService;
import it.romaeur.siw.service.VotoMvpService;
import jakarta.validation.Valid;

@Controller
public class VotoMvpController {
	
	@Autowired PartitaValidator partitaValidator;
	@Autowired GiocatoreService giocatoreService;
	@Autowired PartitaService partitaService;
	@Autowired PrestazioneService prestazioneService; 	
	@Autowired VotoMvpRepository votoMvpRepository;
	@Autowired VotoMvpService votoMvpService;
	@Autowired UserService userService; 
	@Autowired DuplicateVotoValidator duplicateVotoValidator;
	
	
	
	@GetMapping("/mvp/{idPartita}")
	public String mvp(@PathVariable ("idPartita") Long idPartita, Model model) {
		model.addAttribute("giocatori",this.giocatoreService.findAll());	
		Partita partita = this.partitaService.findById(idPartita);
		model.addAttribute("partita",partita );
		return "mvp.html";
	}
	
	@PostMapping("/votoMvp/{idPartita}")
	public String newVotoMvp(@Valid @ModelAttribute("voto") VotoMvp voto, @PathVariable ("idPartita") Long idPartita, @RequestParam Long idVoto,BindingResult bindingResult, Model model) {
		model.addAttribute("giocatori",this.giocatoreService.findAll());
		Partita partita = this.partitaService.findById(idPartita);
		model.addAttribute("partita", partita);
		Giocatore giocatore = this.giocatoreService.findById(idVoto);
		User user = this.userService.getCurrentUser();
		this.duplicateVotoValidator.validate(partita, bindingResult);
		if (!bindingResult.hasErrors()) {
		this.votoMvpService.createNewVotoMvp(voto,user,partita,giocatore);
		model.addAttribute("mvpPartita",this.votoMvpService.calcolaMvp(idPartita));
		model.addAttribute("prestazioni",partita.getPrestazioni());
		return "partita.html";
		}
		return "errorVotoMvp.html";
	} 
	
	

}
