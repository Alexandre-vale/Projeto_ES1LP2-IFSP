package com.alexandre.tryn5.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alexandre.tryn5.model.Jogador;
import com.alexandre.tryn5.repository.JogadorRepository;
import com.alexandre.tryn5.service.JogadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JogadorServiceimpl implements JogadorService{

    @Autowired
    private JogadorRepository jogadorRepo;

    @Override
    public List<Jogador> GetAll() {

        return this.jogadorRepo.findAll();
    }

    @Override
    public Jogador GetByExample(Jogador jogador) {

        final Example<Jogador> example = Example.of(jogador);
        final Optional<Jogador> dbuser = this.jogadorRepo.findOne(example);
        if(dbuser.isPresent()){
            return dbuser.get();
        }
        else{
            return null;
        }
    }


    @Override
    public Jogador UpdateJogador(String id, Jogador newJogadorInfo) {
        
        Jogador oldJogador = new Jogador();
        oldJogador.setId(id);
        final Example<Jogador> example = Example.of(oldJogador);
        final Optional<Jogador> dbuser = this.jogadorRepo.findOne(example);
        if(dbuser.isPresent()){
            Jogador jogadorASalvar = dbuser.get();
            jogadorASalvar.setNome(newJogadorInfo.getNome() != null ? newJogadorInfo.getNome() : jogadorASalvar.getNome());
            jogadorASalvar.setNomeAventuras(newJogadorInfo.getNomeAventuras() != null ? newJogadorInfo.getNomeAventuras() : jogadorASalvar.getNomeAventuras());
            jogadorASalvar.setNomePersonagens(newJogadorInfo.getNomePersonagens() != null ? newJogadorInfo.getNomePersonagens() : jogadorASalvar.getNomePersonagens());
            return this.jogadorRepo.save(jogadorASalvar);
        }
        else{
            return null;
        }
    }

    @Override
    public Jogador CreateJogador(Jogador jogador) {

        return this.jogadorRepo.save(jogador);
    }

    @Override
    public Jogador AddAventura(String id, String aventura) {
        Jogador oldJogador = new Jogador();
        oldJogador.setId(id);
        final Example<Jogador> example = Example.of(oldJogador);
        final Optional<Jogador> dbuser = this.jogadorRepo.findOne(example);
        if(dbuser.isPresent()){
            Jogador JogadorASalvar = dbuser.get();
            if(JogadorASalvar.getNomeAventuras() == null){
                ArrayList<String> mylist = new ArrayList<String>();
                mylist.add(aventura);
                JogadorASalvar.setNomeAventuras(mylist);
            }else{
                ArrayList<String> mylist = new ArrayList<String>();
                mylist.addAll(JogadorASalvar.getNomeAventuras());
                mylist.add(aventura);
                JogadorASalvar.setNomeAventuras(mylist);
            }
            return this.jogadorRepo.save(JogadorASalvar);
        }
        else{
            return null;
        }
    }

    @Override
    public Jogador AddPersonagem(String id, String personagem) {
        Jogador oldJogador = new Jogador();
        oldJogador.setId(id);
        final Example<Jogador> example = Example.of(oldJogador);
        final Optional<Jogador> dbuser = this.jogadorRepo.findOne(example);
        if(dbuser.isPresent()){
            Jogador JogadorASalvar = dbuser.get();
            if(JogadorASalvar.getNomePersonagens() == null){
                ArrayList<String> mylist = new ArrayList<String>();
                mylist.add(personagem);
                JogadorASalvar.setNomePersonagens(mylist);
            }else{
                ArrayList<String> mylist = new ArrayList<String>();
                mylist.addAll(JogadorASalvar.getNomePersonagens());
                mylist.add(personagem);
                JogadorASalvar.setNomePersonagens(mylist);
            }
            return this.jogadorRepo.save(JogadorASalvar);
        }
        else{
            return null;
        }
    }

    @Override
    public Jogador GetById(String id) {
        return this.jogadorRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
    }

    @Override
    public Jogador RemoveAventura(String id, String aventura) {
        Jogador oldmesa = new Jogador();
        oldmesa.setId(id);
        final Example<Jogador> example = Example.of(oldmesa);
        final Optional<Jogador> dbuser = this.jogadorRepo.findOne(example);
        if(dbuser.isPresent()){
            Jogador mesaASalvar = dbuser.get();
            int i;
            ArrayList<String> plist = new ArrayList<String>();
            plist.addAll(mesaASalvar.getNomeAventuras());
            for (i = 0; i < plist.size(); i++) {
                String p = plist.get(i);
                if(aventura.equals(p)){
                    plist.remove(i);
                }
            }
            mesaASalvar.setNomeAventuras(plist);
            return this.jogadorRepo.save(mesaASalvar);
        }
        else{
            return null;
        }
    }

    @Override
    public Jogador RemovePersonagem(String id, String Personagem) {
        Jogador oldmesa = new Jogador();
        oldmesa.setId(id);
        final Example<Jogador> example = Example.of(oldmesa);
        final Optional<Jogador> dbuser = this.jogadorRepo.findOne(example);
        if(dbuser.isPresent()){
            Jogador mesaASalvar = dbuser.get();
            int i;
            ArrayList<String> plist = new ArrayList<String>();
            plist.addAll(mesaASalvar.getNomePersonagens());
            for (i = 0; i < plist.size(); i++) {
                String p = plist.get(i);
                if(Personagem.equals(p)){
                    plist.remove(i);
                }
            }
            mesaASalvar.setNomePersonagens(plist);
            return this.jogadorRepo.save(mesaASalvar);
        }
        else{
            return null;
        }
    }

    @Override
    public ResponseEntity<?> RemoveJogador(String id) {
        final Optional<Jogador> dbmesa = this.jogadorRepo.findById(id);
        if(dbmesa.isPresent()){
            this.jogadorRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    
}
