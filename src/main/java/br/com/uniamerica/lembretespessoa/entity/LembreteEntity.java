package br.com.uniamerica.lembretespessoa.entity;
import br.com.uniamerica.lembretespessoa.entity.AbstractEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "tb_lembretes", schema = "public")
public class LembreteEntity extends AbstractEntity {

    public LembreteEntity(String nomeLembrete, Long id) {
        this.id = id;
        this.nomeLembrete = nomeLembrete;
    }


    @Getter
    @Setter
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn (name = "pessoa_id")
    private PessoaEntity pessoa;

    @Getter
    @Setter
    @Column (name = "nome_lembrete")
    private String nomeLembrete;

    @Getter
    @Setter
    @Column (name = "descricao_lembrete")
    private String descricaoLembrete;
}
