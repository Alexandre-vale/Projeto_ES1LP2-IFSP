package com.alexandre.tryn5.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alexandre.tryn5.model.Jogador;
import com.alexandre.tryn5.model.Mesa;
import com.alexandre.tryn5.repository.MesaRepository;
import com.alexandre.tryn5.service.JogadorService;
import com.alexandre.tryn5.service.MesaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class MesaServiceimpl implements MesaService {
    @Autowired
    private MesaRepository mesaRepo;

    @Autowired
    private JogadorService jogadorSVC;

    @Override
    public List<Mesa> GetAll() {
        
        return this.mesaRepo.findAll();
    }

    @Override
    public ResponseEntity<?> GetByExample(Mesa mesa) {
        final Example<Mesa> example = Example.of(mesa);
        final Optional<Mesa> dbmesa = this.mesaRepo.findOne(example);
        if(dbmesa.isPresent()){
            return new ResponseEntity<>(dbmesa.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Mesa GetById(String id){
        return this.mesaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
    }

    @Override
    public Mesa CreateMesa(Mesa mesa) {

        return this.mesaRepo.save(mesa);
    }

    @Override
    public Mesa UpdateMesa(Mesa mesa, String id) {
            Mesa oldmesa = new Mesa();
            oldmesa.setId(id);
            final Example<Mesa> example = Example.of(oldmesa);
            final Optional<Mesa> dbmesa = this.mesaRepo.findOne(example);
            if(dbmesa.isPresent()){
                Mesa mesaAsalvar = dbmesa.get();
                mesaAsalvar.setDM(mesa.getDM() != null ? mesa.getDM() : mesaAsalvar.getDM());
                mesaAsalvar.setNome(mesa.getNome() != null ? mesa.getNome() : mesaAsalvar.getNome());
                mesaAsalvar.setJogadores(mesa.getJogadores() != null ? mesa.getJogadores() : mesaAsalvar.getJogadores());
                mesaAsalvar.setPersonagens(mesa.getPersonagens() != null ? mesa.getPersonagens() : mesaAsalvar.getPersonagens());
                return this.mesaRepo.save(mesaAsalvar);
            }
        return null;
    }

    @Override
    public Mesa AddPersonagem(String mesaid, String personagem) {
        Mesa oldmesa = new Mesa();
        oldmesa.setId(mesaid);
        final Example<Mesa> example = Example.of(oldmesa);
        final Optional<Mesa> dbuser = this.mesaRepo.findOne(example);
        if(dbuser.isPresent()){
            Mesa mesaASalvar = dbuser.get();
            if(mesaASalvar.getPersonagens() == null){
                ArrayList<String> mylist = new ArrayList<String>();
                mylist.add(personagem);
                mesaASalvar.setPersonagens(mylist);
            }else{
                ArrayList<String> mylist = new ArrayList<String>();
                mylist.addAll(mesaASalvar.getPersonagens());
                mylist.add(personagem);
                mesaASalvar.setPersonagens(mylist);
            }
            return this.mesaRepo.save(mesaASalvar);
        }
        else{
            return null;
        }
    }

    @Override
    public Mesa AddJogador(String mesaid, String jogadorId, String personagem) {
        Mesa oldmesa = new Mesa();
        oldmesa.setId(mesaid);
        final Example<Mesa> example = Example.of(oldmesa);
        final Optional<Mesa> dbuser = this.mesaRepo.findOne(example);
        Jogador jogadorAdd = this.jogadorSVC.GetById(jogadorId);
        if(dbuser.isPresent()){
            Mesa mesaASalvar = dbuser.get(); 
            if(mesaASalvar.getJogadores() == null){
                ArrayList<Jogador> mylist = new ArrayList<Jogador>();
                mylist.add(jogadorAdd);
                mesaASalvar.setJogadores(mylist);
            }else{
                ArrayList<Jogador> mylist = new ArrayList<Jogador>();
                mylist.addAll(mesaASalvar.getJogadores());
                mylist.add(jogadorAdd);
                mesaASalvar.setJogadores(mylist);
            }
            if(mesaASalvar.getPersonagens() == null){
                ArrayList<String> mylist = new ArrayList<String>();
                mylist.add(personagem);
                mesaASalvar.setPersonagens(mylist);
            }else{
                ArrayList<String> mylist = new ArrayList<String>();
                mylist.addAll(mesaASalvar.getPersonagens());
                mylist.add(personagem);
                mesaASalvar.setPersonagens(mylist);
            }
            this.jogadorSVC.AddPersonagem(jogadorId, personagem);
            this.jogadorSVC.AddAventura(jogadorId, mesaASalvar.getNome());
            return this.mesaRepo.save(mesaASalvar);
        }
        else{
            return null;
        }
    }

    @Override
    public ResponseEntity<?> DeleteMesa(String id) {
        final Optional<Mesa> dbmesa = this.mesaRepo.findById(id);
        if(dbmesa.isPresent()){
            this.mesaRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Mesa RemoveJogadorFromMesa(String id, String jogadorid) {
        Mesa oldmesa = new Mesa();
        oldmesa.setId(id);
        final Example<Mesa> example = Example.of(oldmesa);
        final Optional<Mesa> dbuser = this.mesaRepo.findOne(example);
        if(dbuser.isPresent()){
            Mesa mesaASalvar = dbuser.get();
            String currentp;
            int i;
            ArrayList<Jogador> plist = new ArrayList<Jogador>();
            ArrayList<String> charlist = new ArrayList<String>();
            plist.addAll(mesaASalvar.getJogadores());
            charlist.addAll(mesaASalvar.getPersonagens());
            
            for (i = 0; i < plist.size(); i++) {
                Jogador p = plist.get(i);
                currentp = p.getId();
                if(jogadorid.equals(currentp)){
                    this.jogadorSVC.RemovePersonagem(jogadorid, charlist.get(i));
                    plist.remove(i);
                    charlist.remove(i);
                }
            }

            this.jogadorSVC.RemoveAventura(jogadorid, mesaASalvar.getNome());
            mesaASalvar.setPersonagens(charlist);
            mesaASalvar.setJogadores(plist);
            return this.mesaRepo.save(mesaASalvar);
        }
        else{
            return null;
        }
    }
    
}
