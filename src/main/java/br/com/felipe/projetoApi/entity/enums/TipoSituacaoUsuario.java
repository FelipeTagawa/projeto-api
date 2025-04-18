package br.com.felipe.projetoApi.entity.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum TipoSituacaoUsuario {

    ATIVO ("A","ATIVO"),
    INATIVO ("I","INATIVO"),
    PENDENTE ("P","PENDENTE");

    private String codigo;
    private String descricao;

    private TipoSituacaoUsuario(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static TipoSituacaoUsuario doValor(String codigo){
        for (TipoSituacaoUsuario tipo : TipoSituacaoUsuario.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
