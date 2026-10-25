<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Validation de la commande"/>
<%@ include file="/WEB-INF/views/_header.jspf" %>

<h1>Validation de la commande</h1>
<p>Articles : <strong><c:out value="${cart.itemCount}"/></strong> &mdash;
   Total : <strong><fmt:formatNumber value="${cart.total}" type="currency"/></strong></p>

<form method="post" action="${ctx}/checkout" class="form">
  <label>Nom complet
    <input type="text" name="name" value="<c:out value='${param.name}'/>">
    <c:if test="${not empty errors.name}"><span class="error"><c:out value="${errors.name}"/></span></c:if>
  </label>
  <label>E-mail
    <input type="text" name="email" value="<c:out value='${param.email}'/>">
    <c:if test="${not empty errors.email}"><span class="error"><c:out value="${errors.email}"/></span></c:if>
  </label>
  <label>Adresse de livraison
    <textarea name="address" rows="3"><c:out value="${param.address}"/></textarea>
    <c:if test="${not empty errors.address}"><span class="error"><c:out value="${errors.address}"/></span></c:if>
  </label>
  <div class="actions">
    <a class="btn" href="${ctx}/cart">Retour au panier</a>
    <button type="submit" class="btn primary">Confirmer la commande</button>
  </div>
</form>

<%@ include file="/WEB-INF/views/_footer.jspf" %>
