package com.alexandre.tryn5.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document


public class Mesa {
    
    @Id
    private String id;

    private String nome;
    private String DM;
    private List<String> personagens;
    private List<Jogador> jogadores;
    

}
