<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Phohe List">
  <p></p>
  <div class="container">
    <div id="statusMessage" hidden>
      <div id="statusMessageHead" class="panel-heading"></div>
      <div id="statusMessageBody" class="panel-body"></div>
    </div>
  </div>

  <div class="row">
    <div class="container">
      <div class="container">
        <form class="float-right">
          <input name="query" value="${param.query}">
          <button class="btn btn-light">Search</button>
        </form>
      </div>
      <div class="container">
        Found <c:out value="${numberOfPhones}"/> books.
      </div>
    </div>
  </div>
  <div class="panel"></div>
  <div class="row">
    <div class="col-2"></div>
    <div class="col-8">
      <table class="table table-hover table-bordered text-center">
        <thead>
          <tr class="bg-light">
            <td>Image</td>
            <td>
              Brand
              <tags:sortLink sort="brand" order="asc"/><tags:sortLink sort="brand" order="desc"/>
            </td>
            <td>
              Model
              <tags:sortLink sort="model" order="asc"/><tags:sortLink sort="model" order="desc"/>
            </td>
            <td>Color</td>
            <td>
              Display size
              <tags:sortLink sort="displaySizeInches" order="asc"/>
              <tags:sortLink sort="displaySizeInches" order="desc"/>
            </td>
            <td>
              Price
              <tags:sortLink sort="price" order="asc"/>
              <tags:sortLink sort="price" order="desc"/>
            </td>
            <td>Quantity</td>
            <td>Action</td>
          </tr>
        </thead>
        <c:forEach var="book" items="${books}">
          <tr>
            <td class="align-middle">
              <img class="rounded" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${book.imageUrl}">
            </td>
            <td class="align-middle">
              <a href="<c:url value="/productDetails/${book.id}"/>">${book.brand}</a>
            </td>
            <td class="align-middle">
              <a href="<c:url value="/productDetails/${book.id}"/>">${book.model}</a>
            </td>
            <td class="align-middle">
              <ul>
                <c:forEach var="color" items="${book.colors}">
                  <li>${color.code}</li>
                </c:forEach>
              </ul>
            </td>
            <td class="align-middle">${book.displaySizeInches}"</td>
            <td class="align-middle">$ ${book.price}</td>
            <td class="align-middle">
              <input id="quantity${book.id}">
              <p class="text-danger" id="statusMessage${book.id}"></p>
            </td>
            <td class="align-middle">
              <input id="add-to-cart" class="btn btn-outline-dark border-dark" type="button" onclick="addToCart(${book.id});" value="Add to Cart"/>
            </td>
          </tr>
        </c:forEach>
      </table>
      <tags:pages page="${empty param.page or param.page lt 1 ? 1 : param.page}" lastPage="${numberOfPages}"/>
    </div>
  </div>
</tags:master>