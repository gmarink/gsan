<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<logic:notPresent scope="session" name="origemGIS">

<table width="770" cellspacing="4" cellpadding="0">
	<tr>
    	<td>
      	<table width="100%" cellpadding="2" class="footer">
        	<tr>
          		<td  align="left"> 
	          		<logic:present scope="application" name="versaoDataBase"> 
					Banco: ${applicationScope.versaoDataBase}
					</logic:present>  
					<logic:notPresent scope="application" name="versaoDataBase"> 
					PMSS
					</logic:notPresent>
				</td>

<<<<<<< Updated upstream
          		<td align="right">Vers&atilde;o: 5.1.1.57 (Online) 17/02/2014 - 17:01:02 </td>
=======
          		<td align="right">Vers&atilde;o: 5.1.1.57 (Online) 17/02/2014 - 17:01:02 </td>

>>>>>>> Stashed changes
        	</tr>
      	</table>
		</td>
  	</tr>
</table>
</logic:notPresent>
