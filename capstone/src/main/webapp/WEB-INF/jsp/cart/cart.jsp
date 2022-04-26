<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../include/header.jsp" />


<div class="checkout-page">

    <h1 class="cart-heading">
        <i class="fa-solid fa-basket-shopping"></i> My Cart
    </h1>

    <section class="cart-section">
        <h2 class="section-heading">Order Summary</h2>

        <div class="cart-item-box">

            <c:forEach items="${cartProducts}" var="cartList">
                <div class="product-card">
                    <div class="card">
                        <div class="img-box">
                            <img src="${cartList.product_image}" alt="image" class="product-img">
                        </div>
                        <div class="detail">
                            <h3 class="product-name">${cartList.product_name}</h3>
                            <div class="wrapper">
                                <div class="product-qty">

                                    <div class="counter">
                                        <input type="text" id="prod-quant" class="update-quantity" name="quantity" value="${cartList.quantity}">
                                    </div>

                                </div>
                                <div class="price">
                                    <span id="price">$ ${cartList.total}</span>
                                </div>
                            </div>
                        </div>
                        <span class="remove-product-btn">
                    <a href="/cart/deleteItem/${cartList.row_id}" class="fas fa-times" id="remove-product"></a>
                    </span>
                    </div>
                </div>
            </c:forEach>


        </div>

    </section>

    <!-- cart end -->


    <div class="wrapper">



        <!-- discount end -->
        <!-- total section -->

        <div class="amount">

            <div class="subtotal">
                <span>Subtotal</span>
                <span id="subtotal">
                    <fmt:formatNumber value="${subTotal}" type="currency"/>
                </span>
            </div>

            <div class="tax">
                <span>Tax</span>
                <span id="tax">
                    <fmt:formatNumber value="${salesTax}" type="currency"/>
                </span>
            </div>

            <div class="total fs-2 fw-bold">

                <span>Order Total</span>
                <span id="total">
                    <fmt:formatNumber value="${cartTotal}" type="currency"/>
               </span>
            </div>

        </div>

    </div>
    <!-- total end -->

    <!--checkout section-->

    <section class="checkout-section">

        <h2 class="section-heading">Payment Details</h2>

        <div class="payment-form">

            <form action="/shop/checkoutSubmit" method="POST">


                <div class="payment-method">


                    <label class="form-control method" for="cc">
                        <i class="fa-solid fa-credit-card"></i>
                        <span>Credit Card</span>
                        <input type="radio" id="cc" value="CreditCard" name="paymentMethod" checked="checked"/>
                        <i class="fa-regular fa-circle-check checkmark"></i>
                    </label>

                    <label class="form-control method" for="paypal">
                        <i class="fa-brands fa-paypal"></i>
                        <span>Paypal</span>
                        <input type="radio" id="paypal" value="PayPal" name="paymentMethod"/>
                        <i class="fa-regular fa-circle-check checkmark"></i>
                    </label>

                    <label class="form-control method" for="applepay">
                        <i class="fa-brands fa-apple-pay"></i>
                        <span></span>
                        <input type="radio" id="applepay" value="ApplePay" name="paymentMethod"/>
                        <i class="fa-regular fa-circle-check checkmark"></i>
                    </label>


                    <label class="form-control method" for="googlepay">
                        <i class="fa-brands fa-google-pay"></i>
                        <span></span>
                        <input type="radio" id="googlepay" value="GooglePay" name="paymentMethod"/>
                        <i class="fa-regular fa-circle-check checkmark"></i>
                    </label>
                    <c:forEach items='${bindingResult.getFieldErrors("paymentMethod")}' var="error">
                        <div style="color:#943643;">${error.getDefaultMessage()}</div>
                    </c:forEach>

                </div>

                <div class="cc-form">
                    <div class="cardholder-name">
                        <label for="cardholder-name" class="label-default">Cardholder name</label>
                        <input type="text" name="cardholderName" id="cardholder-name" class="input-default">
                        <c:forEach items='${bindingResult.getFieldErrors("cardholderName")}' var="error">
                            <div style="color:#943643;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </div>

                    <div class="card-number">
                        <label for="card-number" class="label-default">Card number</label>
                        <input type="text" name="ccNumber" id="card-number" class="input-default">
                        <jstlC:forEach items='${bindingResult.getFieldErrors("ccNumber")}' var="error">
                            <div style="color:#943643;">${error.getDefaultMessage()}</div>
                        </jstlC:forEach>
                    </div>

                    <div class="input-flex">

                        <div class="expire-date">
                            <label class="label-default">Expiration date</label>

                            <div class="input-flex">

                                <input type="text" name="day" id="expire-day" placeholder="12" min="1" max="12"
                                       class="input-default">
                                /
                                <input type="text" name="month" id="expire-month" placeholder="31" min="1" max="31"
                                       class="input-default">

                            </div>
                        </div>

                        <div class="cvv">
                            <label for="cvv" class="label-default">CVV</label>
                            <input type="text" name="cvv" id="cvv" class="input-default">
                        </div>

                    </div>
                </div>
                <div class="alternate-form">

                    <br>

                </div>

                <br><br><br><br><br>

                <div>
                    <label class="terms-checkbox" for="terms">
                        By checking this box, you are agreeing to our terms of service.
                        <input type="checkbox" id="terms" required>
                        <span class="check"></span>
                    </label>
                </div>

                <br>

                <button class="btn" type="submit">
                    <b>Place your Order</b>
                </button>

            </form>
        </div>
    </section>

</div>

<jsp:include page="../include/footer.jsp" />