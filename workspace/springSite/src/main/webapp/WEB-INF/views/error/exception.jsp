<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<script type="text/javascript" src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	//location.href="${url}"; 
	$(function(){
		$("#errorForm").attr({
			action:"${url}"
		});
		$("#errorForm").submit();
	});
</script>
<form id="errorForm">
	<input type="hidden" name="errCode" value="${errCode}" />
</form>

