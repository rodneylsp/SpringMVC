package com.algaworks.cobranca.controller;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/titulos")
public class TituloController {

    @Autowired
    private Titulos repository;

    private static final String CADASTRO_VIEW = "CadastroTitulo";

    @RequestMapping
    public ModelAndView pesquisar(){

        List<Titulo> titulosList = repository.findAll();
        ModelAndView mv = new ModelAndView("PesquisaTitulos");
        mv.addObject("titulos", titulosList);
        return mv;
    }

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
        mv.addObject(new Titulo());
        return  mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes){
        //spring retorna os erros de validacao no objeto errors

        if(errors.hasErrors()){
            return "CADASTRO_VIEW";
        }

        repository.save(titulo);
        attributes.addFlashAttribute("mensagem", "Título salvo com sucesso");

        return "redirect:/titulos/novo";
    }

    @RequestMapping("{id}")
    public ModelAndView edicao(@PathVariable("id") Titulo titulo){

        //Colocando o PathVariable("[nome do campo]") Objeto, o spring ja associa a um findOne do repository
        //Titulo titulo = repository.findOne(id);

        ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
        mv.addObject(titulo);
        return mv;
    }

    @ModelAttribute("todosStatusTitulo")
    public List<StatusTitulo> todosStatusTitulo(){
        return Arrays.asList(StatusTitulo.values());
    }

}
