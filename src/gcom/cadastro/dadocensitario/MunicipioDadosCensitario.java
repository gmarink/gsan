package gcom.cadastro.dadocensitario;

import gcom.cadastro.geografico.Municipio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class MunicipioDadosCensitario implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** nullable persistent field */
    private Integer numeroPopulacaoUrbana;

    /** nullable persistent field */
    private BigDecimal taxaAnualCrescimentoPopulacaoUrbana;

    /** nullable persistent field */
    private BigDecimal taxaOcupacaoUrbana;

    /** nullable persistent field */
    private Integer numeroPopulacaoRural;

    /** nullable persistent field */
    private BigDecimal taxaCrescimentoPopulacaoRural;

    /** nullable persistent field */
    private BigDecimal taxaOcupacaoRural;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Municipio municipio;

    /** persistent field */
    private gcom.cadastro.dadocensitario.FonteDadosCensitario fonteDadosCensitario;

    /** full constructor */
    public MunicipioDadosCensitario(
            int anoMesReferencia,
            Integer numeroPopulacaoUrbana,
            BigDecimal taxaAnualCrescimentoPopulacaoUrbana,
            BigDecimal taxaOcupacaoUrbana,
            Integer numeroPopulacaoRural,
            BigDecimal taxaCrescimentoPopulacaoRural,
            BigDecimal taxaOcupacaoRural,
            Date ultimaAlteracao,
            Municipio municipio,
            gcom.cadastro.dadocensitario.FonteDadosCensitario fonteDadosCensitario) {
        this.anoMesReferencia = anoMesReferencia;
        this.numeroPopulacaoUrbana = numeroPopulacaoUrbana;
        this.taxaAnualCrescimentoPopulacaoUrbana = taxaAnualCrescimentoPopulacaoUrbana;
        this.taxaOcupacaoUrbana = taxaOcupacaoUrbana;
        this.numeroPopulacaoRural = numeroPopulacaoRural;
        this.taxaCrescimentoPopulacaoRural = taxaCrescimentoPopulacaoRural;
        this.taxaOcupacaoRural = taxaOcupacaoRural;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
        this.fonteDadosCensitario = fonteDadosCensitario;
    }

    /** default constructor */
    public MunicipioDadosCensitario() {
    }

    /** minimal constructor */
    public MunicipioDadosCensitario(
            int anoMesReferencia,
            Municipio municipio,
            gcom.cadastro.dadocensitario.FonteDadosCensitario fonteDadosCensitario) {
        this.anoMesReferencia = anoMesReferencia;
        this.municipio = municipio;
        this.fonteDadosCensitario = fonteDadosCensitario;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Integer getNumeroPopulacaoUrbana() {
        return this.numeroPopulacaoUrbana;
    }

    public void setNumeroPopulacaoUrbana(Integer numeroPopulacaoUrbana) {
        this.numeroPopulacaoUrbana = numeroPopulacaoUrbana;
    }

    public BigDecimal getTaxaAnualCrescimentoPopulacaoUrbana() {
        return this.taxaAnualCrescimentoPopulacaoUrbana;
    }

    public void setTaxaAnualCrescimentoPopulacaoUrbana(
            BigDecimal taxaAnualCrescimentoPopulacaoUrbana) {
        this.taxaAnualCrescimentoPopulacaoUrbana = taxaAnualCrescimentoPopulacaoUrbana;
    }

    public BigDecimal getTaxaOcupacaoUrbana() {
        return this.taxaOcupacaoUrbana;
    }

    public void setTaxaOcupacaoUrbana(BigDecimal taxaOcupacaoUrbana) {
        this.taxaOcupacaoUrbana = taxaOcupacaoUrbana;
    }

    public Integer getNumeroPopulacaoRural() {
        return this.numeroPopulacaoRural;
    }

    public void setNumeroPopulacaoRural(Integer numeroPopulacaoRural) {
        this.numeroPopulacaoRural = numeroPopulacaoRural;
    }

    public BigDecimal getTaxaCrescimentoPopulacaoRural() {
        return this.taxaCrescimentoPopulacaoRural;
    }

    public void setTaxaCrescimentoPopulacaoRural(
            BigDecimal taxaCrescimentoPopulacaoRural) {
        this.taxaCrescimentoPopulacaoRural = taxaCrescimentoPopulacaoRural;
    }

    public BigDecimal getTaxaOcupacaoRural() {
        return this.taxaOcupacaoRural;
    }

    public void setTaxaOcupacaoRural(BigDecimal taxaOcupacaoRural) {
        this.taxaOcupacaoRural = taxaOcupacaoRural;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public gcom.cadastro.dadocensitario.FonteDadosCensitario getFonteDadosCensitario() {
        return this.fonteDadosCensitario;
    }

    public void setFonteDadosCensitario(
            gcom.cadastro.dadocensitario.FonteDadosCensitario fonteDadosCensitario) {
        this.fonteDadosCensitario = fonteDadosCensitario;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

}
