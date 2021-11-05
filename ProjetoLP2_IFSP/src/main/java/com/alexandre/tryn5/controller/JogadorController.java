package com.alexandre.tryn5.controller;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import com.alexandre.tryn5.model.Jogador;
import com.alexandre.tryn5.service.JogadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @PostMapping(value="/jogador")
    public Jogador getOne(@RequestBody Jogador jogador){

       return this.jogadorService.GetByExample(jogador);
    }

    @GetMapping(value="/jogador/testall")
    public List<Jogador> getAlljogadors() {

        return this.jogadorService.GetAll();
    }

    @GetMapping(value="/jogador")
    public Jogador getjogadorByCpf(@RequestParam String id) {

        return this.jogadorService.GetById(id);
    }
        

    @PostMapping(value="/jogador/cadastro")
    public Jogador createjogador(@RequestBody Jogador jogador){

        return this.jogadorService.CreateJogador(jogador);
    }

    @PutMapping(value="/jogador")
    public Jogador Updatejogador(@RequestBody Jogador jogador, @RequestParam String id){
        
        return this.jogadorService.UpdateJogador(id, jogador);
    }

    @PutMapping(value="/jogador/personagens")
    public Jogador AddPersonagem(@RequestParam String id, @RequestParam String personagem){
        
        return this.jogadorService.AddPersonagem(id, personagem);
    }

    @PutMapping(value="/jogador/aventura")
    public Jogador AddAventura(@RequestParam String id, @RequestParam String aventura){
        
        return this.jogadorService.AddAventura(id, aventura);
    }

}
