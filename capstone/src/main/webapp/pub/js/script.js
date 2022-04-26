/* nav bar */

// When clicking the menu bars icon, takes precedence over other overlay menus

let navbar = document.querySelector('.navbar');

document.querySelector('#menu-btn').onclick = () =>{
    navbar.classList.toggle('active');
    searchBar.classList.remove('active');
    cartItem.classList.remove('active');

}

// When clicking the search button, turns it into an "X".
// Brings up search bar overlay and closes cart menu if open.

let searchBar = document.querySelector('.search-bar-container');
let searchBtn = document.querySelector('#search-btn');

document.querySelector('#search-btn').onclick = () =>{

    searchBtn.classList.toggle('fa-times');
    searchBar.classList.toggle('active');
    cartItem.classList.remove('active');
}



window.onscroll = () =>{
    searchBtn.classList.remove('fa-times');
    searchBar.classList.remove('active');
    loginForm.classList.remove('active');
    cartItem.classList.remove('active');
}

let formBtn = document.querySelector('#login-btn');
let loginLink = document.querySelector('#loginLink');
let loginForm = document.querySelector('.login-form-container');
let formClose = document.querySelector('#form-close');

formBtn.addEventListener('click', () =>{
    loginForm.classList.add('active');
});

formClose.addEventListener('click', () =>{
    loginForm.classList.remove('active');
});

loginLink.addEventListener('click', () =>{
    loginForm.classList.add('active');
});

// If toggles cart menu with cart icon and closes search bar if open.

let cartItem = document.querySelector('.cart-items-container');
// let cartLogin = document.querySelector('#cart-loginLink');

document.querySelector('#cart-btn').onclick = () =>{
    cartItem.classList.toggle('active');
    navbar.classList.remove('active');
    searchBar.classList.remove('active');
}

document.querySelector('#cart-loginLink').onclick = () =>{
    cartItem.classList.remove('active');
    navbar.classList.remove('active');
    searchBar.classList.remove('active');
    loginForm.classList.add('active');
}

/* nav bar ends */

/* registration form validation starts*/

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()

/* registration form validation ends*/

/* contact form drop-down on index.html */

let selectInput = document.getElementById("contact-form");


selectInput.addEventListener("input", function(){
    let selectIndex = selectInput.selectedIndex;
    let makeSelection = selectInput.getElementsByTagName("option")[selectIndex].innerHTML;
    let label = document.querySelector('.label-desc');
    label.innerHTML = makeSelection;
    console.log(makeSelection);
});

/* contact form drop-down on index.html ends */


//script for the counter
function increaseCount(a, b) {
    var input = b.previousElementSibling;
    var value = parseInt(input.value, 10);
    value = isNaN(value) ? 0 : value;
    value++;
    input.value = value;

}

function decreaseCount(a, b) {
    var input = b.nextElementSibling;
    var value = parseInt(input.value, 10);
    if (value > 1) {
        value = isNaN(value) ? 0 : value;
        value--;
        input.value = value;
    }
}


function loginMessage() {
    var loginEmail = document.forms["loginForm"]["email"].value;
    if (loginEmail === "") {
        alert("Something Went Wrong, Please Try Again");
        return false;
    }
}

function showPass() {
    var loginPassword = document.getElementById("pass-field");
    if (loginPassword.type === "password") {
        loginPassword.type = "text";
    } else {
        loginPassword.type = "password";
    }
}

