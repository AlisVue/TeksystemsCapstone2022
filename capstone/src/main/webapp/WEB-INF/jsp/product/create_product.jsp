<jsp:include page="../include/header.jsp" />

<form action="/product/create" method="post">

    Name: <input type="text" name="productName">
    <br>
    Description: <input type="text" name="description">
    <br>
    Price: <input type="number" name="price">
    <br>
    Image: <input type="image" name="imageURL">
    <button type="submit">Create Product</button>


</form>


<jsp:include page="../include/footer.jsp" />