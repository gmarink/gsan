package gcom.arrecadacao;

import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.arrecadacao.bean.DadosConteudoCodigoBarrasHelper;
import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarrasTipoPagamento;
import gcom.arrecadacao.bean.RegistroHelperCodigoG;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.conta.ContaGeral;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.SessionBean;


/**
 * Controlador Faturamento CAERN
 *
 * @author Raphael Rossiter
 * @date 30/04/2007
 */
public class ControladorArrecadacaoCAERNSEJB extends ControladorArrecadacao implements SessionBean {

	private static final long serialVersionUID = 1L;

	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA CAERN
	// ==============================================================================================================
	
	/**
	 * retorna o objeto distribuido de acordo comj o tipo de pagamento [UC0264] -
	 * Distribuir Dados do C�digo de Barras [SF0001] - Distribuir Pagamento de
	 * Conta [SF0002] - Distribuir Pagamento de Guia de Pagamento [SF0003] -
	 * Distribuir Pagamento de Documento de Cobram�a [SF0004] - Distribuir
	 * Pagamento de Fatura do Cliente Respons�vel Autor: S�vio Luiz Data:
	 * 15/02/2006
	 * @throws ControladorException 
	 */

	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento(
			String idPagamento, String tipoPagamento, String idEmpresa) throws ControladorException {

		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = new RegistroHelperCodigoBarrasTipoPagamento();
		
		registroHelperCodigoBarrasTipoPagamento = 
			super.distribuirDadosCodigoBarrasPorTipoPagamento(idPagamento, tipoPagamento, idEmpresa);
		
		
		int tipoPagamentoParaComparacao = Integer.parseInt(tipoPagamento.trim());

		switch (tipoPagamentoParaComparacao) {

		// LEGADO - CAERN
		// ===============================================================================================================================
		case 0:

			// seta a matr�cula do im�vel
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
					.substring(0, 6).trim());
			// seta a referencia da data
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
					.substring(6, 9).trim());
			// seta o digito identificador da fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
					.substring(9, 10).trim());
			// seta o digito identificador da fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
					.substring(10, 11).trim());
			// nao utilizado
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
					.substring(11, 24).trim());

			break;
			
		}	
		// ===============================================================================================================================
	
		return registroHelperCodigoBarrasTipoPagamento;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * @author Raphael Rossiter
	 * @date 26/05/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @param sistemaParametro
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasPorTipoPagamento(
			RegistroHelperCodigoBarras registroHelperCodigoBarras, Date dataPagamento, Integer anoMesPagamento,
			BigDecimal valorPagamento, Integer idFormaArrecadacao, SistemaParametro sistemaParametro, Usuario usuarioLogado) 
			throws ControladorException {
		
		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();
		
		int tipoPagamento = Integer.parseInt(registroHelperCodigoBarras
		.getTipoPagamento());

		
		switch (tipoPagamento) {

		//LEGADO - CAERN
		//===============================================================================================================================
		case 0:

			pagamentoHelperCodigoBarras = this
					.processarPagamentosCodigoBarrasContaLegado(
							registroHelperCodigoBarras, sistemaParametro,
							dataPagamento, anoMesPagamento, valorPagamento,
							idFormaArrecadacao);

			break;
			
			
		//===============================================================================================================================
			

		//GERADO PELO GSAN
			
		//===============================================================================================================================
		
		default:
			
			pagamentoHelperCodigoBarras = super.processarPagamentosCodigoBarrasPorTipoPagamento(
					registroHelperCodigoBarras, dataPagamento,
					anoMesPagamento, valorPagamento,
					idFormaArrecadacao, sistemaParametro, usuarioLogado);
			
			break;
		//===============================================================================================================================
			
		}

		return pagamentoHelperCodigoBarras;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 * 
	 * LEGADO CONTA CAERN
	 * 
	 * @autor: Raphael Rossiter, Raphael Rossiter 
	 * @data: 30/04/2007, 09/01/2007
	 */
	private PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasContaLegado(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean matriculaImovelInvalida = false;

		int anoMes = 0;
		Integer idImovelNaBase = null;
		Integer matriculaImovel = null;

		boolean anoMesReferencia = false;

		// Valida matr�cula im�vel
		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento1());

		if (matriculaImovelInvalida) {

			descricaoOcorrencia = "M�TRICULA DO IM�VEL INV�LIDA";

		} else {

			// Verifica se existe a matricula do im�vel na base
			matriculaImovel = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1());

			// [FS0008] - Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {
				int digitoModulo11 = Util.obterDigitoVerificadorModuloCAERN(""
						+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel.toString()
						+ digitoModulo11);
			}

			idImovelNaBase = null;

			try {

				idImovelNaBase = repositorioImovel
						.recuperarMatriculaImovel(matriculaImovel);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			// Se o id do imovel pesquisado na base for diferente de nulo
			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATR�CULA DO IM�VEL N�O CADASTRADA";
			}
		}

		// Valida mes/ano referencia
		anoMesReferencia = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento2());

		if (!anoMesReferencia) {

			anoMes = this.obterMesAnoReferencia(Integer
					.parseInt(registroHelperCodigoBarras
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2()));

		} else {
			descricaoOcorrencia = "ANO/M�S DE REFER�NCIA DA CONTA INV�LIDA";
		}

		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;
			Integer idConta = null;

			int anoMesReferenciaInt = anoMes;

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);

			try {

				idConta = repositorioFaturamento
				.pesquisarExistenciaContaComSituacaoAtual(imovel,
				anoMesReferenciaInt);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			/*
             * Alterado por Raphael Rossiter em 09/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da conta e N�O com
             * a localidade do im�vel.
             */
			if (idConta == null || idConta.equals("")) {
				descricaoOcorrencia = "CONTA INEXISTENTE";
				
				try {
                	idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidade(idImovelNaBase);

                } catch (ErroRepositorioException e) {
                	throw new ControladorException("erro.sistema", e);
                }
                
			}
			else{
				
                try {
                	idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorConta(idConta);

                } catch (ErroRepositorioException e) {
                	throw new ControladorException("erro.sistema", e);
                }
                
			}

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(anoMes);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecada��o da tabela sistema parametro ent�o seta o ano mes da
			 * data de debito
			 */

			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);
			} else {

				/*
				 * caso contrario seta o o ano mes arrecada��o da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			pagamento.setDebitoTipo(null);

			// Verifica se o id da conta � diferente de nulo
			if (idConta != null) {

				ContaGeral conta = new ContaGeral();
				conta.setId(idConta);
				pagamento.setContaGeral(conta);

			} else {
				pagamento.setContaGeral(null);
			}

			pagamento.setGuiaPagamento(null);

			// Verifica se o id da conta � diferente de nulo
			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);

			} else {
				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.CONTA);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);

			if (idImovelNaBase != null) {

				pagamento.setImovel(imovel);

			} else {
				pagamento.setImovel(null);
			}

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaPagamento);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());
			
			/*
			 * Alteracao referente ao relatorio de Float - Francisco 14/07/08
			 */
			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			DocumentoTipo documentoTipoAgregador = new DocumentoTipo();
			documentoTipoAgregador.setId(DocumentoTipo.CONTA);
			pagamento.setDocumentoTipoAgregador(documentoTipoAgregador);
			
			pagamento.setDataProcessamento(new Date());

			colecaoPagamnetos.add(pagamento);

		} else {

			// Atribui o valor 2(N�O) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que ser�o retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
		
	
	/**
	 * [UC0270] Apresentar An�lise do Movimento dos Arrecadadores
	 *
	 * @author Raphael Rossiter
	 * @date 08/11/2008
	 *
	 * @param registroHelperCodigoG
	 * @param arrecadadorMovimentoItemHelper
	 * @throws ControladorException
	 */
	
	
	public void distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(RegistroHelperCodigoG registroHelperCodigoG,
			ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper) throws ControladorException {
		
		/*
		 * Caso o tipo de pagamento corresponda a: Conta (valor =
		 * 3), Guia de Pagamento (valor = 4) ou Documento de
		 * Cobran�a (valor = 5), exibir a matr�cula do im�vel
		 * retornada pelo [UC0264]
		 */
		
		// Alterado por: Yara T. Souza
		// Solicitado por : Adriano Brito
		// Data : 13/11/2008
		// Coloquei esse m�todo com essa verifica��o apenas no ControladorArrecadacaoCAERN ,
        // no ControladorArrecadacao
		// Essa condi��o est� comentada.
		
		if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAERN))) {

			Integer matriculaImovel = new Integer(
					registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1());

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {

				int digitoModulo11 = Util
						.obterDigitoVerificadorModuloCAERN(""
								+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel
						.toString()
						+ digitoModulo11);

			}

			arrecadadorMovimentoItemHelper
					.setIdentificacao(matriculaImovel.toString());

			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

		} else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());

			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

		} else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
		} else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOCUMENTO_COBRANCA);
		} else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL);
		}
	}

	/**
	 * [UC0270] Apresentar An�lise do Movimento dos Arrecadadores
	 * 
	 * O sistema captura os dados referentes ao conte�do do do c�digo de barras
	 * 
	 * [SF0003] Apresentar Dados do Conte�do do C�digo de Barras
	 * 
	 * @author Raphael Rossiter,Vivianne Sousa
	 * @data 22/03/2006,27/11/2008
	 * 
	 * @param registroHelperCodigoG
	 * @return DadosConteudoCodigoBarrasHelper
	 */
	public DadosConteudoCodigoBarrasHelper apresentarDadosConteudoCodigoBarras(
			RegistroHelperCodigoG registroHelperCodigoG)
			throws ControladorException {

		DadosConteudoCodigoBarrasHelper retorno = new DadosConteudoCodigoBarrasHelper();

		retorno.setIdentificacaoProduto(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getIdProduto());
		retorno.setIdentificacaoSegmento(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getIdSegmento());
		retorno.setIdentificacaoValorRealOUReferencia(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getIdValorReal());
		retorno.setDigitoVerificadorGeral(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getDigitoVerificadorGeral());
		retorno
				.setValorPagamento(Util
						.formatarMoedaReal(Util
								.formatarMoedaRealparaBigDecimalComUltimos2CamposDecimais(registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getValorPagamento())));

		String tipoPagamento = registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getTipoPagamento();

		// Alterado por: Vivianne Sousa
		// Solicitado por : Adriano Brito
		// Data : 27/11/2008
		// Coloquei esse m�todo com essa verifica��o apenas no ControladorArrecadacaoCAERN ,
        // no ControladorArrecadacao
		// Essa condi��o est� comentada.
		
		if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAERN
								.toString())) {

			retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

			// Verifica se existe a matricula do im�vel na base
			Integer matriculaImovel = new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1());

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {

				int digitoModulo11 = Util.obterDigitoVerificadorModuloCAERN(""
						+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel.toString()
						+ digitoModulo11);

				retorno.setMatriculaImovel(matriculaImovel.toString());

				Collection colecaoClienteImovel = this.getControladorImovel()
						.pesquisarImovel(matriculaImovel.toString(), null,
								null, null, null, null, null, null, null, null,
								null, null, null, false, false, 0);

				ClienteImovel clienteImovel = (ClienteImovel) Util
						.retonarObjetoDeColecao(colecaoClienteImovel);

				if (clienteImovel != null && clienteImovel.getImovel() != null
						&& !clienteImovel.getImovel().equals("")) {

					retorno.setCodigoLocalidade(clienteImovel.getImovel()
							.getLocalidade().getId().toString());
				}

			}

			int anoMesReferenciaConta = this.obterMesAnoReferencia(Integer
					.parseInt(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2()));

			retorno.setMesAnoReferenciaConta(Util
					.formatarAnoMesParaMesAno(anoMesReferenciaConta));

			retorno.setDigitoVerificadorContaModulo10(new Long(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento5()).toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA
								.toString())) {

			retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

			retorno.setCodigoLocalidade(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1()).toString());

			retorno.setMatriculaImovel(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());

			retorno
					.setMesAnoReferenciaConta(Util
							.formatarMesAnoSemBarraParaMesAnoComBarra(registroHelperCodigoG
									.getRegistroHelperCodigoBarras()
									.getRegistroHelperCodigoBarrasTipoPagamento()
									.getIdPagamento4()));

			retorno.setDigitoVerificadorContaModulo10(new Integer(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento5()).toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);

			retorno.setCodigoLocalidade(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1()).toString());
			retorno.setMatriculaImovel(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());
			retorno.setCodigoTipoDebito(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento4()).toString());
			retorno.setAnoEmissaoGuiaPagamento(new Integer(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento5()).toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOCUMENTO_COBRANCA);

			retorno.setMatriculaImovel(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());
			retorno.setSequencialDocumentoCobranca(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3());
			retorno.setCodigoTipoDocumento(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento4());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL);

			retorno.setCodigoCliente(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());
			retorno.setMesAnoReferenciaConta(Util
					.formatarAnoMesParaMesAno(new Integer(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2()).intValue()));
			retorno.setDigitoVerificadorContaModulo10(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5());
			retorno.setSequencialFaturaClienteResponsavel(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento6());

		}

		return retorno;

	}

	
}
