package br.com.alura.screenmatch.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.screenmatch.domain.filme.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
}
