package com.alexandre.tryn5.service;

import java.util.List;

import com.alexandre.tryn5.model.Jogador;

public interface JogadorService {

    public List<Jogador> GetAll();

    public Jogador GetByExample(Jogador jogador);

    public Jogador UpdateJogador(String id, Jogador newJogadorInfo);

    public Jogador CreateJogador(Jogador jogador);

    public Jogador GetById(String id);

    public Jogador AddAventura(String id, String aventura);

    public Jogador AddPersonagem(String id, String personagem);
}
