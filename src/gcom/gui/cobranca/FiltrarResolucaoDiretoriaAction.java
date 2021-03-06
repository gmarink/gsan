package gcom.gui.cobranca;

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarResolucaoDiretoriaAction extends GcomAction {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterResolucaoDiretoria");

		FiltrarResolucaoDiretoriaActionForm filtrarResolucaoDiretoriaActionForm = (FiltrarResolucaoDiretoriaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa todo o formul�rio para evitar "sujeiras" na tela
		String numero = "";
		String assunto = "";
		String dataInicio = "";
		String dataFim = "";
	    String indicadorParcelamentoUnico = "";
	    String indicadorUtilizacaoLivre = "";
	    String indicadorDescontoSancoes = "";
	    
	    String indicadorParcelasEmAtraso = "";
	    String idParcelasEmAtraso = "";
	    String indicadorParcelamentoEmAndamento = "";
	    String idParcelamentoEmAndamento = "";
	    String indicadorNegociacaoSoAVista = "";
	    String indicadorDescontoSoEmContaAVista = "";
	    String indicadorParcelamentoLojaVirtual = "";

		// Verifica se voltou do ManterResolucaoDiretoria e para isso, verifica
		// se a pagina��o � nula
		if (httpServletRequest.getParameter("page.offset") == null) {

			// Variaveis
			// Recupera os par�metros do form, setando-os na sess�o para poder
			// recuper�-los e coloc�-los novamente na p�gina caso o usu�rio
			// deseje voltar para o filtro
			numero = filtrarResolucaoDiretoriaActionForm.getNumero();
			sessao.setAttribute("numero", numero);
			
			assunto = filtrarResolucaoDiretoriaActionForm.getAssunto();
			sessao.setAttribute("assunto", assunto);
			
			dataInicio = filtrarResolucaoDiretoriaActionForm.getDataInicio();
			sessao.setAttribute("dataInicio", dataInicio);
			
			dataFim = filtrarResolucaoDiretoriaActionForm.getDataFim();
			sessao.setAttribute("dataFim", dataFim);
			
			indicadorParcelamentoUnico = filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoUnico();
			sessao.setAttribute("indicadorParcelamentoUnico", indicadorParcelamentoUnico);
			
			indicadorUtilizacaoLivre = filtrarResolucaoDiretoriaActionForm.getIndicadorUtilizacaoLivre();
			sessao.setAttribute("indicadorUtilizacaoLivre", indicadorUtilizacaoLivre);
			
			indicadorDescontoSancoes = filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSancoes();
			sessao.setAttribute("indicadorDescontoSancoes", indicadorDescontoSancoes);
			
			indicadorParcelasEmAtraso = filtrarResolucaoDiretoriaActionForm.getIndicadorParcelasEmAtraso();
			sessao.setAttribute("indicadorParcelasEmAtraso", indicadorParcelasEmAtraso);
			
			idParcelasEmAtraso = filtrarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso();
			sessao.setAttribute("idParcelasEmAtraso", idParcelasEmAtraso);
			
			indicadorParcelamentoEmAndamento = filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoEmAndamento();
			sessao.setAttribute("indicadorParcelamentoEmAndamento", indicadorParcelamentoEmAndamento);
			
			idParcelamentoEmAndamento = filtrarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento();
			sessao.setAttribute("idParcelamentoEmAndamento", idParcelamentoEmAndamento);
		    
			indicadorNegociacaoSoAVista = filtrarResolucaoDiretoriaActionForm.getIndicadorNegociacaoSoAVista();
			sessao.setAttribute("indicadorNegociacaoSoAVista", indicadorNegociacaoSoAVista);
			
			indicadorDescontoSoEmContaAVista = filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSoEmContaAVista();
			sessao.setAttribute("indicadorDescontoSoEmContaAVista",indicadorDescontoSoEmContaAVista);

			indicadorParcelamentoLojaVirtual = filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoLojaVirtual();
			sessao.setAttribute("indicadorParcelamentoLojaVirtual",indicadorParcelamentoLojaVirtual);
		} else {
			// Recupera as vari�veis digitadas anteriormente para coloc�-los
			// novamente no filtro, pois devido ao esquema de
			// pagina��o, cada vez que o usu�rio seleciona a visualiza��o das
			// anteriores ou pr�ximas resolu��es de diretoria � efetuado um novo
			// filtro
			numero = (String) sessao.getAttribute("numero");
			assunto = (String) sessao.getAttribute("assunto");
			dataInicio = (String) sessao.getAttribute("dataInicio");
			dataFim = (String) sessao.getAttribute("dataFim");
			indicadorParcelamentoUnico = (String) sessao.getAttribute("indicadorParcelamentoUnico");
			indicadorUtilizacaoLivre = (String) sessao.getAttribute("indicadorUtilizacaoLivre");
			indicadorDescontoSancoes = (String) sessao.getAttribute("indicadorDescontoSancoes");
			
			indicadorParcelasEmAtraso = (String)sessao.getAttribute("indicadorParcelasEmAtraso");
			if (sessao.getAttribute("idParcelasEmAtraso") != null){
				idParcelasEmAtraso = (String)sessao.getAttribute("idParcelasEmAtraso");
			}
			indicadorParcelamentoEmAndamento = (String)sessao.getAttribute("indicadorParcelamentoEmAndamento");
			
			if(sessao.getAttribute("idParcelamentoEmAndamento")!= null){
				idParcelamentoEmAndamento = (String)sessao.getAttribute("idParcelamentoEmAndamento");
			}
			indicadorNegociacaoSoAVista = (String)sessao.getAttribute("indicadorNegociacaoSoAVista");
			indicadorDescontoSoEmContaAVista = (String)sessao.getAttribute("indicadorDescontoSoEmContaAVista");
			indicadorParcelamentoLojaVirtual = (String)sessao.getAttribute("indicadorParcelamentoLojaVirtual");
		}

		// Cria o filtro
		FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();

		// Ordena a pesquisa por um par�metro pr�-definido
		filtroResolucaoDiretoria.setCampoOrderBy("numeroResolucaoDiretoria");

		if (dataInicio != null && !dataInicio.trim().equals("")
				&& dataFim != null && !dataFim.trim().equals("")) {
			if ((Util.converteStringParaDate(dataInicio)).compareTo(Util
					.converteStringParaDate(dataFim)) >= 0) {
				throw new ActionServletException(
						"atencao.termino_vigencia.anterior.inicio_vigencia");
			}
		}

		boolean peloMenosUmParametroInformado = false;

		// Neste ponto o filtro � criado com os par�metros informados na p�gina
		// de filtrar resolu��o de diretoria para ser executada a pesquisa no
		// ExibirManterResolucaoDiretoriaAction

		if (numero != null && !numero.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.NUMERO, numero));
		}

		if (assunto != null && !assunto.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroResolucaoDiretoria.adicionarParametro(new ComparacaoTexto(
					FiltroResolucaoDiretoria.DESCRICAO, assunto));
		}

		if (dataInicio != null && !dataInicio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			Date dataInicioFormatada = Util.converteStringParaDate(dataInicio);

			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.DATA_VIGENCIA_INICIO,
					dataInicioFormatada));
		}

		if (dataFim != null && !dataFim.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			Date dataFimFormatada = Util.converteStringParaDate(dataFim);

			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.DATA_VIGENCIA_FIM,
					dataFimFormatada));
		}
		
		// Indicador Parcelamento Unico
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoUnico().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_UNICO, filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoUnico() ) );
			peloMenosUmParametroInformado = true;			
		}
		
		// Indicador de Utilizacao Livre
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorUtilizacaoLivre().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_UTILIZACAO_LIVRE, filtrarResolucaoDiretoriaActionForm.getIndicadorUtilizacaoLivre() ) );
			peloMenosUmParametroInformado = true;			
		}
		
		// Indicador de Descontos e Sancoes
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSancoes().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_DESCONTOS_SANCOES, filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSancoes() ) );
			peloMenosUmParametroInformado = true;			
		}
		
		//Indicador de Parcelas em Atraso
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorParcelasEmAtraso().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_PARCELAS_EM_ATRASO, filtrarResolucaoDiretoriaActionForm.getIndicadorParcelasEmAtraso() ) );
			peloMenosUmParametroInformado = true;	
			
			//Id da RD Parcelas em Atraso 
			if (filtrarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso().equals( ConstantesSistema.SIM.toString() ) ){
				filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.ID_RD_PARCELAS_EM_ATRASO, filtrarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso() ) );
			}
		}
		
		//Indicador de Parcelamento em Andamento
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoEmAndamento().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_EM_ANDAMENTO, filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoEmAndamento() ) );
			peloMenosUmParametroInformado = true;	
			
			//Id da RD Parcelamento em Andamento
			if (filtrarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento().equals( ConstantesSistema.SIM.toString() ) ){
				filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.ID_RD_PARCELAMENTO_EM_ANDAMENTO, filtrarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento() ) );
			}
		}
		
		//Indicador de Negocia��o s� a vista
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorNegociacaoSoAVista().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_NEGOCIACAO_SO_A_VISTA, filtrarResolucaoDiretoriaActionForm.getIndicadorNegociacaoSoAVista() ) );
			peloMenosUmParametroInformado = true;			
		}
		
		//
		if (!filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSoEmContaAVista().equals(ConstantesSistema.TODOS.toString())){
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_DESCONTO_SO_EM_CONTA_A_VISTA,filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSoEmContaAVista()));
			peloMenosUmParametroInformado = true;
		}
		
		//Indicador Parcelamento Loja Virtual
		if (!filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoLojaVirtual().equals(ConstantesSistema.TODOS.toString())){
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_LOJA_VIRTUAL,filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoLojaVirtual()));
			peloMenosUmParametroInformado = true;
		}
		
		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pelo um request uma vari�vel para o
		// ExibirManterResolucaoDiretoriaAction e nele verificar se ir� para o
		// atualizar ou para o manter
		if (filtrarResolucaoDiretoriaActionForm.getAtualizar() != null
				&& filtrarResolucaoDiretoriaActionForm.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",
					filtrarResolucaoDiretoriaActionForm.getAtualizar());
		}

		// Manda o filtro pela sessao para o
		// ExibirManterResolucaoDiretoriaAction
		sessao.setAttribute("filtroResolucaoDiretoria",
				filtroResolucaoDiretoria);

		return retorno;
	}
}
