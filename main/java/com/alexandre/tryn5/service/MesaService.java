package com.alexandre.tryn5.service;

import java.util.List;

import com.alexandre.tryn5.model.Mesa;

import org.springframework.http.ResponseEntity;





public interface MesaService {
    
    public List<Mesa> GetAll();

    public ResponseEntity<?> GetByExample(Mesa user);

    public Mesa GetById(String id);

    public Mesa CreateMesa(Mesa mesa);

    public Mesa UpdateMesa(Mesa mesa, String id);

    public Mesa AddPersonagem(String mesaId, String personagem);

    public Mesa AddJogador(String mesaId, String jogadorId);
}
