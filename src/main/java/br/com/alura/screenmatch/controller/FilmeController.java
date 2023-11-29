package br.com.alura.screenmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.screenmatch.domain.filme.DadosAlteracaoFilme;
import br.com.alura.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.alura.screenmatch.domain.filme.Filme;
import br.com.alura.screenmatch.domain.repository.FilmeRepository;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

	@Autowired
	private FilmeRepository filmeRepository;
	
    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model) {
    	if(id!=null) {
    		var filme = filmeRepository.getReferenceById(id);
    		model.addAttribute("filme", filme);
    	}
        return "filmes/formulario";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", filmeRepository.findAll());
        return "filmes/listagem";
    }

    @PostMapping
    public String cadastraFilme(DadosCadastroFilme dados) {
        var filme = new Filme(dados);
        filmeRepository.save(filme);

        return "redirect:/filmes";
    }
    @DeleteMapping
    @Transactional
    public String removeFilme(Long id) {
    	filmeRepository.deleteById(id);
    	return "redirect:/filmes";
    }
    @PutMapping
    @Transactional
    public String atualizaFilme(DadosAlteracaoFilme dadosAlteracaoFilme) {
    	var filme = filmeRepository.getReferenceById(dadosAlteracaoFilme.id());
    	filme.atualizaDados(dadosAlteracaoFilme);
    	
    	return "redirect:/filmes";
    }

}
