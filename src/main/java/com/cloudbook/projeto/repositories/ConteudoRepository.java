package com.cloudbook.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudbook.projeto.domain.Conteudo;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, Integer>{

}
