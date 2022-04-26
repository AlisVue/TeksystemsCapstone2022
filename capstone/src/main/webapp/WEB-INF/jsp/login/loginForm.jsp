<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<jsp:include page="../include/header.jsp" />

<form action="/login/loginSubmit" method="POST">

    Username : <input type="text" name="username">
    <br>
    Password : <input type="text" name="password">
    <br>
    <button type="submit">Submit</button>
</form>


<jsp:include page="../include/footer.jsp" />