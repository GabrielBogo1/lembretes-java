package br.com.uniamerica.lembretespessoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

@Entity
@Table(name = "tb_pessoas", schema = "public")
public class PessoaEntity extends AbstractEntity{

    public PessoaEntity(Long id, String nome) {
        this.nome = nome;
    }

    @Getter
    @Setter
    @Column (name = "nome_pessoa")
    private String nome;

    @Getter
    @Setter
    @Column (name = "id_pessoa")
    @Id
    private Long id;

    @Getter
    @Setter
    @OneToMany (mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<LembreteEntity> lembretes;
}


