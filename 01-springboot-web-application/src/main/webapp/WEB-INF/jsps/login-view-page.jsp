<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h4>Enter login details.</h4>
	<form method="post">
		<font color="red">${errorMessage}</font><br /> 
		Name: <input type="text" name="name" /><br /> 
		Password: <input type="password" name="password"><br /> 
		<input type="submit" value="login" />
	</form>
</div>
<%@ include file="common/footer.jspf" %>
