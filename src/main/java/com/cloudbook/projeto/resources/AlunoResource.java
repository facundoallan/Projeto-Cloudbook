package com.cloudbook.projeto.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudbook.projeto.domain.Aluno;
import com.cloudbook.projeto.services.AlunoService;

@RestController
@RequestMapping(value="/alunos")
public class AlunoResource {

	@Autowired
	private AlunoService service;
	@RequestMapping(value="/{id}",method =RequestMethod.GET)
	
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Aluno obj=service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	
	}
}
