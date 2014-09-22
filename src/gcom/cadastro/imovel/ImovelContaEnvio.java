package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ImovelContaEnvio extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private short indicadorUso;
    private Date ultimaAlteracao;
    private Short indicadorClienteResponsavel;
    
    @SuppressWarnings("rawtypes")
	private Set imovels;

    public final static Integer ENVIAR_CLIENTE_RESPONSAVEL = new Integer("1");
    public final static Integer ENVIAR_IMOVEL = new Integer("2");
    public final static Integer NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL = new Integer("3");
    public final static Integer ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO = new Integer("9");
    public final static Integer ENVIAR_PARA_EMAIL = new Integer("4");
    public final static Integer ENVIAR_PARA_IMOVEL_E_PARA_EMAIL = new Integer("5");
    public final static Integer ENVIAR_CONTA_BRAILLE = new Integer("6");
    public final static Integer ENVIAR_CONTA_BRAILLE_RESPONSAVEL = new Integer("7");
    
    public ImovelContaEnvio() {
    }
    
    public ImovelContaEnvio(Integer id) {
    	this.id = id;
    }
    
    public ImovelContaEnvio(Integer icteId, String icteDscontaenvio, short icteIcuso, Date icteTmultimaalteracao, Set imovels) {
        this.id = icteId;
        this.descricao = icteDscontaenvio;
        this.indicadorUso = icteIcuso;
        this.ultimaAlteracao = icteTmultimaalteracao;
        this.imovels = imovels;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icteId", getId().toString())
            .toString();
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set getImovels() {
		return imovels;
	}

	public void setImovels(Set imovels) {
		this.imovels = imovels;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	@Override
	public Filtro retornaFiltro() {
		FiltroImovelContaEnvio filtro = new FiltroImovelContaEnvio();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelContaEnvio.ID,
				this.getId()));
		return filtro;
	}


	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}


	public Short getIndicadorClienteResponsavel() {
		return indicadorClienteResponsavel;
	}


	public void setIndicadorClienteResponsavel(Short indicadorClienteResponsavel) {
		this.indicadorClienteResponsavel = indicadorClienteResponsavel;
	}
}
