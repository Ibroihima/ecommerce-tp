<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Panier"/>
<%@ include file="/WEB-INF/views/_header.jspf" %>

<h1>Mon panier</h1>

<c:choose>
  <c:when test="${cart.empty}">
    <p class="empty">Votre panier est vide.</p>
    <a class="btn" href="${ctx}/products">Voir le catalogue</a>
  </c:when>
  <c:otherwise>
    <table class="table">
      <thead>
        <tr><th>Produit</th><th>Prix unitaire</th><th>Quantité</th><th>Sous-total</th><th></th></tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${cart.items}">
        <tr>
          <td><c:out value="${item.product.icon}"/> <c:out value="${item.product.name}"/></td>
          <td><fmt:formatNumber value="${item.product.price}" type="currency"/></td>
          <td>
            <form method="post" action="${ctx}/cart" class="inline">
              <input type="hidden" name="action" value="update">
              <input type="hidden" name="id" value="${item.product.id}">
              <input type="number" name="quantity" min="0" max="${item.product.stock}" value="${item.quantity}">
              <button type="submit">OK</button>
            </form>
          </td>
          <td><fmt:formatNumber value="${item.subtotal}" type="currency"/></td>
          <td>
            <form method="post" action="${ctx}/cart" class="inline">
              <input type="hidden" name="action" value="remove">
              <input type="hidden" name="id" value="${item.product.id}">
              <button type="submit" class="danger">Retirer</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="3" class="right"><strong>Total</strong></td>
          <td colspan="2"><strong><fmt:formatNumber value="${cart.total}" type="currency"/></strong></td>
        </tr>
      </tfoot>
    </table>

    <div class="actions">
      <form method="post" action="${ctx}/cart" class="inline">
        <input type="hidden" name="action" value="clear">
        <button type="submit" class="danger">Vider le panier</button>
      </form>
      <a class="btn" href="${ctx}/products">Continuer mes achats</a>
      <a class="btn primary" href="${ctx}/checkout">Passer la commande</a>
    </div>
  </c:otherwise>
</c:choose>

<%@ include file="/WEB-INF/views/_footer.jspf" %>
