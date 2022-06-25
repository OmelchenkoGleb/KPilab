$(".user").focusin(function(){
    $(".inputUserIcon").css("color", "#e74c3c");
}).focusout(function(){
    $(".inputUserIcon").css("color", "white");
});

$(".pass").focusin(function(){
    $(".inputPassIcon").css("color", "#e74c3c");
}).focusout(function(){
    $(".inputPassIcon").css("color", "white");
});

document.querySelectorAll("nav a.btn").forEach(el =>{
    el.addEventListener()
})
$( "nav a.btn" ).on( "click", function() {
    console.log( $( this ).text() );
});