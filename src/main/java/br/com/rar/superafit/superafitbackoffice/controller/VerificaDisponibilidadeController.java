package br.com.rar.superafit.superafitbackoffice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/trainning-available")
public class VerificaDisponibilidadeController {

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Void> verificaDisponibilidade() {
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}