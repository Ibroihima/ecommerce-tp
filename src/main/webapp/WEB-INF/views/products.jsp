<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Catalogue"/>
<%@ include file="/WEB-INF/views/_header.jspf" %>

<h1>Catalogue</h1>

<form class="filters" method="get" action="${ctx}/products">
  <input type="text" name="q" placeholder="Rechercher un produit..." value="<c:out value='${q}'/>">
  <select name="category">
    <option value="">Toutes les catégories</option>
    <c:forEach var="cat" items="${categories}">
      <option value="<c:out value='${cat}'/>" ${cat == selectedCategory ? 'selected' : ''}><c:out value="${cat}"/></option>
    </c:forEach>
  </select>
  <button type="submit">Filtrer</button>
</form>

<c:if test="${empty products}">
  <p class="empty">Aucun produit ne correspond à votre recherche.</p>
</c:if>

<div class="grid">
  <c:forEach var="p" items="${products}">
    <article class="card">
      <div class="icon"><c:out value="${p.icon}"/></div>
      <span class="tag"><c:out value="${p.category}"/></span>
      <h3><c:out value="${p.name}"/></h3>
      <p class="desc"><c:out value="${p.description}"/></p>
      <p class="price"><fmt:formatNumber value="${p.price}" type="currency"/></p>
      <c:choose>
        <c:when test="${p.stock > 0}">
          <form method="post" action="${ctx}/cart">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="id" value="${p.id}">
            <button type="submit" class="btn">Ajouter au panier</button>
          </form>
        </c:when>
        <c:otherwise><span class="out">Rupture de stock</span></c:otherwise>
      </c:choose>
    </article>
  </c:forEach>
</div>

<%@ include file="/WEB-INF/views/_footer.jspf" %>
