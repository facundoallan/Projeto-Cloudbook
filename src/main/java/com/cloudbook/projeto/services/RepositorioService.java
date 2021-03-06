package com.cloudbook.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cloudbook.projeto.domain.Aluno;
import com.cloudbook.projeto.domain.Repositorio;
import com.cloudbook.projeto.domain.dto.RepositorioDTO;
import com.cloudbook.projeto.domain.dto.RepositorioNewDTO;
import com.cloudbook.projeto.repositories.AlunoRepository;
import com.cloudbook.projeto.repositories.RepositorioRepository;
import com.cloudbook.projeto.security.UserSS;
import com.cloudbook.projeto.services.exceptions.AuthorizationException;
import com.cloudbook.projeto.services.exceptions.ObjectNotFoundException;

@Service
public class RepositorioService {
	
	@Autowired
	private RepositorioRepository repo;
	
	@Autowired 
	private AlunoRepository repo2;
	
	@Autowired
	private AlunoService alunoService;
	
	
		public Repositorio find(Integer id) {
			
			 Optional<Repositorio> obj = repo.findById(id);
			 
			 return obj.orElseThrow(() -> new ObjectNotFoundException(
					 "Repositorio não encontrado! Id: " + id + ", Tipo: " + Repositorio.class.getName())); 
			} 
		
		public Repositorio insert(Repositorio repositorio) {
			repositorio.setId(null);
			return repo.save(repositorio);
		}
		
		public Repositorio update(Repositorio repositorio) {
			Repositorio newobj =find(repositorio.getId());
			updateData(newobj,repositorio);
			return repo.save(newobj);
		}
		
		public void delete(Integer id) {
			find(id);
			repo.deleteById(id);
		}
		
		public List<Repositorio> findAll(){
			return repo.findAll();
		}
		
		public Page<Repositorio> findPage(Integer page, Integer linesPerPage,String orderBy,String direction){
			PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
			return repo.findAll(pageRequest);
			
		}
		
		public Repositorio fromDTO(RepositorioDTO objDto) {
			
			return new Repositorio(objDto.getId(),objDto.getNome(),
					objDto.getData(),objDto.getDescricao(),null);
		}
		

		public Repositorio fromDTO(RepositorioNewDTO objDto) {
				
			Repositorio repositorio= new Repositorio(null,objDto.getNome(),objDto.getData(),objDto.getDescricao(),objDto.getAluno());
			
			return repositorio;
		
		}
		

		private void updateData(Repositorio newobj,Repositorio obj) {
			newobj.setNome(obj.getNome());
			newobj.setData(obj.getData());
			newobj.setDescricao(obj.getDescricao());
			
		}
		
		/*
		public Page<Repositorio> search(List<Integer> id, Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			//List<Repositorio> repositorios = repo.findAll();
			return repo.consulta(id, pageRequest);	
		}
		*/

		public Page<Repositorio> search(List<Integer>id,Integer page, Integer linesPerPage,String orderBy,String direction){
			
			PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
			
			return repo.search(id,pageRequest);
		}
		
		
		

	}

