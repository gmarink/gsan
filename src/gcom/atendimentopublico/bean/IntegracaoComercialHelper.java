package gcom.atendimentopublico.bean;

import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class IntegracaoComercialHelper implements Serializable {
	private static final long serialVersionUID = 1L;

    private gcom.cadastro.imovel.Imovel imovel;
    
    private gcom.atendimentopublico.ligacaoagua.LigacaoAgua ligacaoAgua;
    
    private gcom.atendimentopublico.ordemservico.OrdemServico ordemServico;
    
    private LigacaoEsgoto ligacaoEsgoto;
    
    private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;
    
    private HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico;
    
    private DadosEfetuacaoCorteLigacaoAguaHelper dadosEfetuacaoCorteLigacaoAguaHelper;
    
    private boolean veioEncerrarOS;
    
    private String qtdParcelas;
    
    private Integer localArmazenagemHidrometro;
    
    private String matriculaImovel;
    
    private String situacaoHidrometroSubstituido;   
    
    private Usuario usuarioLogado;
    
	public IntegracaoComercialHelper(){}

	public gcom.cadastro.imovel.Imovel getImovel() {
		return imovel;
	}

	public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
		this.imovel = imovel;
	}

	public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof IntegracaoComercialHelper)) {
            return false;
        }
        IntegracaoComercialHelper castOther = (IntegracaoComercialHelper) other;

        return (this.getImovel().getId().equals(castOther.getImovel().getId()));
    }

	public gcom.atendimentopublico.ligacaoagua.LigacaoAgua getLigacaoAgua() {
		return ligacaoAgua;
	}

	public void setLigacaoAgua(
			gcom.atendimentopublico.ligacaoagua.LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public gcom.atendimentopublico.ordemservico.OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(
			gcom.atendimentopublico.ordemservico.OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public LigacaoEsgoto getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}

	public void setLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}

	public boolean isVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(boolean veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return hidrometroInstalacaoHistorico;
	}

	public void setHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}

	public HidrometroInstalacaoHistorico getHidrometroSubstituicaoHistorico() {
		return hidrometroSubstituicaoHistorico;
	}

	public void setHidrometroSubstituicaoHistorico(
			HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico) {
		this.hidrometroSubstituicaoHistorico = hidrometroSubstituicaoHistorico;
	}

	public Integer getLocalArmazenagemHidrometro() {
		return localArmazenagemHidrometro;
	}

	public void setLocalArmazenagemHidrometro(Integer localArmazenagemHidrometro) {
		this.localArmazenagemHidrometro = localArmazenagemHidrometro;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getSituacaoHidrometroSubstituido() {
		return situacaoHidrometroSubstituido;
	}

	public void setSituacaoHidrometroSubstituido(
			String situacaoHidrometroSubstituido) {
		this.situacaoHidrometroSubstituido = situacaoHidrometroSubstituido;
	}

	public DadosEfetuacaoCorteLigacaoAguaHelper getDadosEfetuacaoCorteLigacaoAguaHelper() {
		return dadosEfetuacaoCorteLigacaoAguaHelper;
	}

	public void setDadosEfetuacaoCorteLigacaoAguaHelper(
			DadosEfetuacaoCorteLigacaoAguaHelper dadosEfetuacaoCorteLigacaoAguaHelper) {
		this.dadosEfetuacaoCorteLigacaoAguaHelper = dadosEfetuacaoCorteLigacaoAguaHelper;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
