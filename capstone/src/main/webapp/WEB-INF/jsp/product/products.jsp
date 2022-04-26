<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />

<h1>All Bounce Castles</h1>

<%--<c:forEach items="${products}" var="product">--%>
<%--    ${product.name}--%>
<%--    ${product.description}--%>
<%--    ${product.price}--%>

<%--</c:forEach>--%>

    <section>
        <h2>Products</h2>
        <p>Choose which size bounce castle you would like to rent for your party!</p>

        <div class="row">
            <div class="column">
                <div class="card">
                    <h3>Large Castle</h3>
                    <p>This is our largest bounce house!</p>
                    <button type="button" class="add-product">Add to cart!</button>
                    <img src="../../../pub/image/Large.jpg" alt="#" class="product-picture"/>
                </div>
            </div>

            <div class="column">
                <div class="card">
                    <h3>Medium Castle</h3>
                    <p>This is our medium bounce house!</p>
                    <button type="button" class="add-product">Add to cart!</button>
                    <img src="../../../pub/image/Medium.jpg" alt="#" class="product-picture"/>
                </div>
            </div>

            <div class="column">
                <div class="card">
                    <h3>Small Castle</h3>
                    <p>This is our small bounce house!</p>
                    <button type="button" class="add-product">Add to cart!</button>
                    <img src="../../../pub/image/Small.jpg" alt="#" class="product-picture"/>
                </div>
            </div>

            <div class="column">
                <div class="card">
                    <h3>X-Small Castle</h3>
                    <p>This is our smallest bounce house!</p>
                    <button type="button" class="add-product">Add to cart!</button>
                    <img src="../../../pub/image/XSmall.jpg" alt="#" class="product-picture"/>
                </div>
            </div>
        </div>
    </section>



<jsp:include page="../include/footer.jsp" />