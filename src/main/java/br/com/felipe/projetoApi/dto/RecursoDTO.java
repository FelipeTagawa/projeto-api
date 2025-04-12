package br.com.felipe.projetoApi.dto;

import br.com.felipe.projetoApi.entity.RecursoEntity;
import br.com.felipe.projetoApi.entity.UsuarioEntity;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class RecursoDTO {

    public RecursoDTO() {
    }

    public RecursoDTO(RecursoEntity recurso){
        BeanUtils.copyProperties(recurso, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecursoDTO that = (RecursoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    private Long id;
    private String nome;
    private String chave;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
}
