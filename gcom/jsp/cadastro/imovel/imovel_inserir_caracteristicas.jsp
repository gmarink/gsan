<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.util.ConstantesSistema"%>

<%@ page import="gcom.cadastro.imovel.FonteAbastecimento"%>

<%@ page import="gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirImovelActionForm" dynamicJavascript="false" page="3"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script>
<!-- Begin

     var bCancel = false;

    function validateInserirImovelActionForm(form) {
        if (bCancel)
      return true;
        else
       return testarCampoDecimalValorZero(document.InserirImovelActionForm.areaConstruida, '�rea Constru�da')
              && testarCampoDecimalValorZero(document.InserirImovelActionForm.reservatorioInferior, 'Volume Reservat�rio Inferior')
              && testarCampoDecimalValorZero(document.InserirImovelActionForm.reservatorioSuperior, 'Volume Reservat�rio Superior')
              && testarCampoDecimalValorZero(document.InserirImovelActionForm.piscina, 'Volume Piscina Im�vel')
              && validateCaracterEspecial(form) && validateLong(form) && validateDecimal(form) && validateRequired(form)
              && validarAreaConstruida() && validarEsgotamento(form) && validarIndicadorNivelInstalacaoEsgoto();
   }
   
   function validarIndicadorNivelInstalacaoEsgoto(){
	   var form = document.forms[0];
   		if(form.apresentarIndicadorNivelInstalacaoEsgoto == null || form.apresentarIndicadorNivelInstalacaoEsgoto == ''){
   			return true;
   		}else{
   	     	if(form.indicadorNivelInstalacaoEsgoto[0].checked == false && form.indicadorNivelInstalacaoEsgoto[1].checked == false){
   				alert("Informe o n�vel para instala��o de esgoto");
   				return false;
   			}else{
   				return true;
   			}
   		}
   }
   

    function required () {
     this.ag = new Array("pavimentoCalcada", "Informe Pavimento Cal�ada.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("pavimentoRua", "Informe Pavimento Rua.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("fonteAbastecimento", "Informe Fonte de Abastecimento.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("situacaoLigacaoAgua", "Informe Situa��o Liga��o �gua.", new Function ("varName", " return this[varName];"));
     this.al = new Array("situacaoLigacaoEsgoto", "Informe Situa��o Liga��o Esgoto.", new Function ("varName", " return this[varName];"));
     this.am = new Array("perfilImovel", "Informe Perfil do Im�vel.", new Function ("varName", " return this[varName];"));
///     this.an = new Array("poco", "Informe Po�o.", new Function ("varName", " return this[varName];"));

    }
   
   

    function caracteresespeciais () {
     ////this.aa = new Array("areaConstruida", "�rea Constru�da deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
  //   this.ab = new Array("reservatorioInferior", "Volume Reservat�rio Inferior deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
  ////  this.ac = new Array("reservatorioSuperior", "Volume Reservat�rio Superior deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
   //  this.ad = new Array("piscina", "Volume Piscina Im�vel deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
    }


    function IntegerValidations () {
    }
    function FloatValidations () {
     this.ai = new Array("areaConstruida", "�rea Constru�da deve somente conter n�meros decimais positivos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("reservatorioInferior", "Volume Reservat�rio Inferior deve somente conter n�meros decimais positivos.", new Function ("varName", " return this[varName];"));
     this.al = new Array("reservatorioSuperior", "Volume Reservat�rio Superior deve somente conter n�meros decimais positivos.", new Function ("varName", " return this[varName];"));
     this.am = new Array("piscina", "Volume Piscina Im�vel deve somente conter n�meros decimais positivos.", new Function ("varName", " return this[varName];"));
    }
//End -->
</script>

<script>


function pesquisaEnter(tecla) {

  var form = document.InserirImovelActionForm;

  if (document.all) {
    var codigo = event.keyCode;
  } else {
    var codigo = tecla.which;
  }
  if(form.idCliente.value != ''){
    if (codigo == 13) {
      form.action = "exibirInserirImovelCaracteristicasAction.do";
      form.submit();
    } else {
      return true;
    }
  }
}

  function validarAreaConstruida(){
    var form = document.InserirImovelActionForm;
    if (form.areaConstruida.value == '' && form.faixaAreaConstruida.value == '-1'){
		alert("Informe �rea Constru�da.");
		return false;
    }
    return true;
  }


  function desabilitaCampoAreaConstruida(){
    var form = document.InserirImovelActionForm;
    if (form.areaConstruida.value != ''){
      form.faixaAreaConstruida.value = '-1';
      form.faixaAreaConstruida.disabled = true;
    }else{
      form.faixaAreaConstruida.disabled = false;
    }
  }

  function desabilitaCampoReservatorioInferior(){
    var form = document.InserirImovelActionForm;
    if (form.reservatorioInferior.value != ''){
      form.faixaReservatorioInferior.value = '-1';
      form.faixaReservatorioInferior.disabled = true;
    }else{
      form.faixaReservatorioInferior.disabled = false;
    }
  }

  function desabilitaCampoReservatorioSuperior(){
    var form = document.InserirImovelActionForm;
    if (form.reservatorioSuperior.value != ''){
      form.faixaResevatorioSuperior.value = '-1';
      form.faixaResevatorioSuperior.disabled = true;
    }else{
      form.faixaResevatorioSuperior.disabled = false;
    }
  }

  function desabilitaCampoPiscina(){
    var form = document.InserirImovelActionForm;
    if (form.piscina.value != ''){
      form.faixaPiscina.value = '-1';
      form.faixaPiscina.disabled = true;
    }else{
      form.faixaPiscina.disabled = false;
    }
  }
  
function habilitacaoCampoPoco(listBoxFonte){
 	
 	var form = document.forms[0];
 	
 	var poco = form.poco;
 	
 	var indicadorCalcularVolume = listBoxFonte.options[listBoxFonte.options.selectedIndex].id;
 	
 	if (indicadorCalcularVolume > 1){
 		poco.value = "-1";
 		poco.disabled = true;
 	}
 	else{
 		poco.disabled = false;
 	}
}

function habilitacaoEsgotamento(listBoxSituacaoEsgoto){
 	
 	var form = document.forms[0];
 	
 	var esgotamento = form.idLigacaoEsgotoEsgotamento;
 	
 	var indicadorFaturamentoEsgoto = 
 	listBoxSituacaoEsgoto.options[listBoxSituacaoEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamentoEsgoto > 1){
 		esgotamento.value = "-1";
 		esgotamento.disabled = true;
 	}
 	else{
 		esgotamento.disabled = false;
 	}
}

function validarEsgotamento(form){
	
	var esgotamento = form.idLigacaoEsgotoEsgotamento;
	var listBoxSituacaoEsgoto = form.situacaoLigacaoEsgoto;
	
	var indicadorFaturamentoEsgoto = 
 	listBoxSituacaoEsgoto.options[listBoxSituacaoEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamentoEsgoto < 2 && esgotamento.value == "-1"){
 		alert("Informe Esgotamento");
 		form.idLigacaoEsgotoEsgotamento.focus();
 		
 		return false;
 	}
 	else{
 		return true;
 	}
}
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:desabilitaCampoPiscina()
;desabilitaCampoReservatorioSuperior();desabilitaCampoReservatorioInferior()
;desabilitaCampoAreaConstruida();habilitacaoEsgotamento(document.forms[0].situacaoLigacaoEsgoto);
habilitacaoCampoPoco(document.forms[0].fonteAbastecimento);">

<div id="formDiv">
<html:form action="/inserirImovelWizardAction" method="post" onsubmit="return validateInserirImovelActionForm(this);">

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=5"/>



<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
<input type="hidden" name="numeroPagina" value="6"/>
  <tr>
    <td width="150" valign="top" class="leftcoltext">
      <div align="center">
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>

	<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>

	<%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      </div></td>
    <td width="650" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Im�vel</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td>Para identificar &agrave; faixa da &aacute;rea contru&iacute;da do im&oacute;vel, informe os dados abaixo:</td>
		    <logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelInserirAbaCaracteristica', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
			</logic:present>
			<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
			</logic:notPresent>       
       	</tr>
        </table>
        <table width="100%" border="0">
        <tr>
          <td height="23"><table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td>
                  <table width="100%" border="0">
                    <tr>
                      <td width="27%" height="30"><strong>&Aacute;rea Constru&iacute;da:<font color="#FF0000">*</font></strong></td>
                      <td><html:text maxlength="10" size="10" property="areaConstruida" style="text-align: right;" onkeyup="javaScript:desabilitaCampoAreaConstruida();formataValorMonetario(this, 8);"/>
                        m<sup>2</sup>&nbsp;&nbsp;
						<html:select property="faixaAreaConstruida">
						 <html:option value="-1">&nbsp;</html:option>
                         <html:options collection="areaContruidaFaixas" labelProperty="faixaCompletaComId" property="id"/>
                        </html:select>
                      </td>
                    </tr>
                    <tr>
                      <td height="30"><p><strong>Vol. Reservat&oacute;rio Inferior:</strong></p></td>
                      <td><html:text maxlength="6" size="6" property="reservatorioInferior" style="text-align: right;" onkeyup="javaScript:desabilitaCampoReservatorioInferior();formataValorMonetario(this, 6);"/>
                        m<sup>3</sup>&nbsp;&nbsp;
						<html:select property="faixaReservatorioInferior">
						 <html:option value="-1">&nbsp;</html:option>
                         <html:options collection="reservatorioVolumeFaixas" labelProperty="faixaCompletaComId" property="id"/>
                        </html:select>
                      </td>
                    </tr>
                    <tr>
                      <td height="30"><p><strong>Vol. Reservat&oacute;rio Superior:</strong></p></td>
                      <td><html:text maxlength="6" size="6" property="reservatorioSuperior" style="text-align: right;" onkeyup="javaScript:desabilitaCampoReservatorioSuperior();formataValorMonetario(this, 6);"/>
                        m<sup>3</sup>&nbsp;&nbsp;
						<html:select property="faixaResevatorioSuperior">
                         <html:option value="-1">&nbsp;</html:option>
                         <html:options collection="reservatorioVolumeFaixas" labelProperty="faixaCompletaComId" property="id"/>
                        </html:select>
                      </td>
                    </tr>
                    <tr>
                      <td height="30"><p><strong>Vol. Piscina Im&oacute;vel:</strong></p></td>
                      <td><html:text maxlength="6" size="6" property="piscina" style="text-align: right;" onkeyup="javaScript:desabilitaCampoPiscina();formataValorMonetario(this, 6);"/>
                        m<sup>3</sup>&nbsp;&nbsp;
                        <html:select property="faixaPiscina">
			 			<html:option value="-1">&nbsp;</html:option>
                         <html:options collection="piscinaVolumeFaixas" labelProperty="faixaCompletaComId" property="id"/>
                        </html:select>
                      </td>
                    </tr>

                    <tr>
                      <td height="30"><p><strong>Jardim:</strong></p></td>
                      <td>
						<strong><html:radio property="jardim" value="1" />Sim</strong> 
    					<strong><html:radio property="jardim" value="2" />N�o</strong>
                      </td>
                    </tr>
                </table>
                
                </td>
              </tr>
          </table>
          
          </td>
        </tr>
        <tr>
          <td height="24"><hr></td>
        </tr>
        <tr>
          <td height="24">
          
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>
                  <table width="100%" border="0">
                    <tr>
                      <td width="27%" height="30"><strong>Pavimento Cal&ccedil;ada:<font color="#FF0000">*</font></strong></td>
                      <td>
                      	<html:select property="pavimentoCalcada" style="width: 200px">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                         <html:options collection="pavimetoCalcadas" labelProperty="descricaoComId" property="id"/>
                        </html:select>
                      </td>
                    </tr>
                    <tr>
                      <td height="30"><strong>Pavimento Rua:<font color="#FF0000">*</font></strong></td>
                      <td>
						<html:select property="pavimentoRua" style="width: 200px">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                         <html:options collection="pavimentoRuas" labelProperty="descricaoComId" property="id"/>
                        </html:select></td>
                    </tr>
                    <tr>
                      <td height="30"><strong>Fonte de Abastecimento:<font color="#FF0000">*</font></strong></td>
                      <td>
						
						<bean:define name="InserirImovelActionForm" property="fonteAbastecimento" id="idFonteAbastecimento"/>
						
						<html:select property="fonteAbastecimento"
						onchange="habilitacaoCampoPoco(this);" style="width: 150px">
				
						<option id="2" value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
						
						<logic:present name="fonteAbastecimentos">
																						    	
							<logic:iterate name="fonteAbastecimentos" id="fonteAbastecimento" type="FonteAbastecimento">
															      				
								<option value="<%="" + fonteAbastecimento.getId() %>" 
								<%=idFonteAbastecimento.toString().equals(fonteAbastecimento.getId().toString()) ? " selected " : " "%>
								id="<%= "" + fonteAbastecimento.getIndicadorPermitePoco().toString() %>">
								<%="" + fonteAbastecimento.getDescricaoComId() %></option>
								
							</logic:iterate>
													
						</logic:present>
				
						</html:select>
						</td>
                    </tr>
                    <tr>
                      <td height="30"><strong>Situa&ccedil;&atilde;o Liga&ccedil;&atilde;o &Aacute;gua:<font color="#FF0000">*</font></strong></td>
                      <td>
						<html:select property="situacaoLigacaoAgua" style="width: 150px">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                         <html:options collection="ligacaoAguaSituacaos" labelProperty="descricaoComId" property="id"/>
                        </html:select></td>
                    </tr>
                    <tr>
                      <td height="30"><strong>Situa&ccedil;&atilde;o Liga&ccedil;&atilde;o Esgoto:<font color="#FF0000">*</font></strong></td>
                      <td>
                        
                        <bean:define name="InserirImovelActionForm" property="situacaoLigacaoEsgoto" id="idLigacaoEsgoto"/>
                        
                        <html:select property="situacaoLigacaoEsgoto"
						onchange="habilitacaoEsgotamento(this);" style="width: 150px">
						
						<option id="2" value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
						
						<logic:present name="ligacaoEsgotoSituacaos">
																						    	
							<logic:iterate name="ligacaoEsgotoSituacaos" id="situacaoEsgoto" type="LigacaoEsgotoSituacao">
															      				
								<option value="<%="" + situacaoEsgoto.getId() %>" 
								<%=idLigacaoEsgoto.toString().equals(situacaoEsgoto.getId().toString()) ? " selected " : " "%>
								id="<%= situacaoEsgoto.getIndicadorFaturamentoSituacao().toString()%>">
								<%="" + situacaoEsgoto.getDescricaoComId() %></option>
								
							</logic:iterate>
													
						</logic:present>
						
						</html:select>
				
                        </td>
                    </tr>
                    
                    <tr>
                      <td height="30"><strong>Esgotamento:<font color="#FF0000">*</font></strong></td>
                      <td>
                        <html:select property="idLigacaoEsgotoEsgotamento" style="width: 150px">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                          <html:options collection="colecaoLigacaoEsgotoEsgotamento" labelProperty="descricao" property="id"/>
                        </html:select></td>
                    </tr>
                     
                    <tr>
                      <td height="30"><strong>Perfil do Im&oacute;vel:<font color="#FF0000">*</font></strong></td>
                      <td>
						<html:select property="perfilImovel">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                          <html:options collection="perfilImoveis" labelProperty="descricaoComId" property="id"/>
                        </html:select></td>
                    </tr>
                    
                    
                     <!-- Existe n�vel para instala��o de esgoto -->  
                    <logic:present name="apresentarIndicadorNivelInstalacaoEsgoto">
                    	<input type="hidden" name="apresentarIndicadorNivelInstalacaoEsgoto" value="${apresentarIndicadorNivelInstalacaoEsgoto}" />
	                    <tr>
							<td><strong>Existe n&iacute;vel para instala&ccedil;&atilde;o de esgoto:<font color="#FF0000">*</font></strong></td>
							<td><strong> <span class="style1"> <html:radio
								property="indicadorNivelInstalacaoEsgoto" value="<%=ConstantesSistema.SIM.toString()%>"
								tabindex="13" /> Sim <html:radio
								property="indicadorNivelInstalacaoEsgoto" value="<%=ConstantesSistema.NAO.toString()%>"/>
							N�o </span></strong></td>
						</tr>
					</logic:present>
                    
                    <tr>
                      <td height="30"><strong>Po&ccedil;o:<font color="#FF0000"></font></strong></td>
                      <td>
                        <html:select property="poco" style="width: 350px">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                          <html:options collection="pocoTipos" labelProperty="descricaoComId" property="id"/>
                        </html:select></td>
                    </tr>
                    <tr>
                      <td height="30"><strong>Tipo de Despejo:</strong></td>
                      <td>
                        <html:select property="tipoDespejo" style="width: 200px">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                          <html:options collection="tipoDespejos" labelProperty="descricaoComId" property="id"/>
                        </html:select></td>
                    </tr>

                    <tr>
                      <td height="30"><strong>Tipo de Habita&ccedil;&atilde;o:</strong></td>
                      <td>
                        <html:select property="imovelTipoHabitacao" style="width: 200px">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                          <html:options collection="tipoHabitacao" labelProperty="descricaoComId" property="id"/>
                        </html:select></td>
                    </tr>

                    <tr>
                      <td height="30"><strong>Tipo de Propriedade:</strong></td>
                      <td>
                        <html:select property="imovelTipoPropriedade" style="width: 150px">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                          <html:options collection="tipoPropriedade" labelProperty="descricaoComId" property="id"/>
                        </html:select></td>
                    </tr>
                    
                    <tr>
                      <td height="30"><strong>Tipo de Constru&ccedil;&atilde;o:</strong></td>
                      <td>
                        <html:select property="imovelTipoConstrucao" style="width: 150px">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                          <html:options collection="tipoConstrucao" labelProperty="descricaoComId" property="id"/>
                        </html:select></td>
                    </tr>
                    
                    <tr>
                      <td height="30"><strong>Tipo de Cobertura:</strong></td>
                      <td>
                        <html:select property="imovelTipoCobertura" style="width: 200px">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
				         </html:option>
                          <html:options collection="tipoCobertura" labelProperty="descricaoComId" property="id"/>
                        </html:select></td>
                    </tr>
                </table>
                
                </td>
              </tr>
               <tr>
                <td>
					<div align="right">
						<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=5"/>
					</div>
				</td>
               </tr>
          </table></td>
        </tr>
      </table>
    </table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>



<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirImovelWizardAction.do?concluir=true&action=inserirImovelCaracteristicasAction'); }
</script>
</html:html>
