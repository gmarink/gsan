/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.cadastro.localidade;

import gcom.cadastro.geografico.Municipio;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.DescriptorEntity;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SetorComercial extends ObjetoTransacao implements Serializable, DescriptorEntity{
	
	private static final long serialVersionUID = 1L;
	
	public final static Short BLOQUEIO_INSERIR_IMOVEL_SIM = new Short("1");
	public final static Short BLOQUEIO_INSERIR_IMOVEL_NAO = new Short("2");

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int codigo;

    /** persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Municipio municipio;

    /** persistent field */
    private gcom.cadastro.localidade.Localidade localidade;
    
    /** nullable persistent field */
    private Short indicadorSetorAlternativo;
    
    private Short indicadorBloqueio = BLOQUEIO_INSERIR_IMOVEL_NAO;

	/** full constructor */
    public SetorComercial(int codigo, String descricao, Short indicadorUso,
            Date ultimaAlteracao, Municipio municipio,
            gcom.cadastro.localidade.Localidade localidade, Short indicadorSetorAlternativo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
        this.localidade = localidade;
        this.indicadorSetorAlternativo = indicadorSetorAlternativo;
    }

    /** default constructor */
    public SetorComercial() {
    }

    /** minimal constructor */
    public SetorComercial(int codigo, String descricao, Municipio municipio,
            gcom.cadastro.localidade.Localidade localidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.municipio = municipio;
        this.localidade = localidade;
    }

    /** constructor */
    public SetorComercial(Integer id, int codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public gcom.cadastro.localidade.Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(gcom.cadastro.localidade.Localidade localidade) {
        this.localidade = localidade;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
	
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Short getIndicadorSetorAlternativo() {
		return indicadorSetorAlternativo;
	}

	public void setIndicadorSetorAlternativo(Short indicadorSetorAlternativo) {
		this.indicadorSetorAlternativo = indicadorSetorAlternativo;
	}
	
	public Short getIndicadorBloqueio() {
		return indicadorBloqueio;
	}

	public void setIndicadorBloqueio(Short indicadorBloqueio) {
		this.indicadorBloqueio = indicadorBloqueio;
	}

	public Filtro retornaFiltro(){
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.MUNICIPIO);
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.LOCALIDADE);
		filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.ID, this.getId()));
		return filtroSetorComercial; 
	}
	
	// m�todo para exibir a descricao de um Setor comercial no registrar transacao
	public String getDescricaoParaRegistroTransacao(){
		if (getDescricao() != null){
			return getCodigo() + " - " + getDescricao();
		}else{
			return null;
		}
	}
	
	@Override
	public void initializeLazy() {
		if (getLocalidade() != null){
			getLocalidade().getDescricao();
		}
	}
}
