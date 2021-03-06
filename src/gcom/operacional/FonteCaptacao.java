package gcom.operacional;


import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FonteCaptacao extends TabelaAuxiliarAbreviada {
	
	private static final long serialVersionUID = 1L;
    
	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private TipoCaptacao tipoCaptacao;

	public TipoCaptacao getTipoCaptacao() {
		return tipoCaptacao;
	}

	public void setTipoCaptacao(TipoCaptacao tipoCaptacao) {
		this.tipoCaptacao = tipoCaptacao;
	}

	/** full constructor */
    public FonteCaptacao(String descricao, 
    	String descricaoAbreviada, 
    	short indicadorUso, 
    	Date ultimaAlteracao) {
        
    	this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public FonteCaptacao() { }

    /** minimal constructor */
    public FonteCaptacao(short indicadorUso, Date ultimaAlteracao) {
    	
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
   

}
