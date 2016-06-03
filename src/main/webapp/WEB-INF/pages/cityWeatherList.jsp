<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h1>Australian city weather</h1>
		
	<form:form>
		<table>
			<tr>
			    <td>Select a City:</td>
			    <td>
			    <form:select path="cityName" id="cityName" onchange="selectCity(this);">
				<c:if test="${empty command.cityName}">
					<form:option value=""/>	
				</c:if>
			    	<form:options items="${command.cityList}"/>
			    </form:select> 
			    </td>
			</tr>
		</table>
		<c:if test="${not empty command.cityName}">		
		<table border=1 style="border-collapse: collapse;">
			<tr><td>&nbsp; City &nbsp;</td><td>&nbsp; ${command.cityName}&nbsp;</td></tr>
			<tr><td>&nbsp; Updated time &nbsp;</td><td>&nbsp; ${command.updatedTime}&nbsp;</td></tr>
			<tr><td>&nbsp; Weather &nbsp;</td><td>&nbsp; ${command.weather}&nbsp;</td></tr>
			<tr><td>&nbsp; Temperature &nbsp;</td><td>&nbsp; ${command.temperature}&nbsp;</td></tr>
			<tr><td>&nbsp; Wind &nbsp;</td><td>&nbsp; ${command.wind}&nbsp;</td></tr>
		</table>
		</c:if>
	</form:form>
	
	
<script>
function selectCity(me) {
	var str = location.href;
	var idx = str.indexOf("//");
	if (idx != -1) {
		idx = str.indexOf("/", idx + 2);
		if (idx != -1) {
			idx = str.indexOf("/", idx + 1);
			if (idx != -1) {
				str = str.substring(0, idx +1);
			}
		}
	}
	location = str + me.value;
}
</script>
</body>
</html>