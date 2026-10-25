<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Commande confirmée"/>
<%@ include file="/WEB-INF/views/_header.jspf" %>

<div class="success">
  <h1>✅ Merci pour votre commande !</h1>
  <p>Référence : <strong><c:out value="${order.reference}"/></strong></p>
  <p>Client : <c:out value="${order.customerName}"/> (<c:out value="${order.email}"/>)</p>
  <p>Livraison : <c:out value="${order.address}"/></p>
  <p>Articles : <c:out value="${order.itemCount}"/> &mdash;
     Total payé : <strong><fmt:formatNumber value="${order.total}" type="currency"/></strong></p>
  <a class="btn primary" href="${ctx}/products">Retour au catalogue</a>
</div>

<%@ include file="/WEB-INF/views/_footer.jspf" %>
