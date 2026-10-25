<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Erreur"/>
<%@ include file="/WEB-INF/views/_header.jspf" %>

<div class="empty">
  <h1>Oups, une erreur est survenue</h1>
  <p>La page demandée est introuvable ou une erreur interne s'est produite.</p>
  <a class="btn primary" href="${ctx}/products">Retour au catalogue</a>
</div>

<%@ include file="/WEB-INF/views/_footer.jspf" %>
