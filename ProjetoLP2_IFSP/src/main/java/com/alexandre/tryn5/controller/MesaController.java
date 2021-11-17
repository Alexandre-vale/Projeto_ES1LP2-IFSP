package com.alexandre.tryn5.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.alexandre.tryn5.model.Mesa;
import com.alexandre.tryn5.service.MesaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
public class MesaController {
    @Autowired
    private MesaService mesaService;

    @PostMapping(value="/mesa")
    public ResponseEntity<?> getOne(@RequestBody Mesa mesa){
       return this.mesaService.GetByExample(mesa);
    }

    @GetMapping(value="/mesa/testall")
    public List<Mesa> getAllMesas() {
        return this.mesaService.GetAll();
    }
        
    @GetMapping(value="/mesa")
    public Mesa getById(@RequestParam String id) {
        return this.mesaService.GetById(id);
    }


    @PostMapping(value="/mesa/cadastro") 
    public Mesa createMesa(@RequestBody Mesa mesa){
        return this.mesaService.CreateMesa(mesa);
    }

    @PutMapping(value="/mesa")
    public Mesa updateMesa(@RequestBody Mesa mesa, @RequestParam String id){
       return this.mesaService.UpdateMesa(mesa, id);
    }

    @PutMapping(value="/mesa/jogador")
    public Mesa AddJogador(@RequestParam String jogadorid, @RequestParam String id, @RequestParam String personagem){
       return this.mesaService.AddJogador(id, jogadorid, personagem);
    }

    @PutMapping(value="/mesa/personagem")
    public Mesa AddPersonagem(@RequestParam String id, @RequestParam String personagem){
       return this.mesaService.AddPersonagem(id, personagem);
    }


}