$(document).ready(function() {
    $(".btn-login").on("click", function() {
        $.ajax({
            method: "POST",
            url: "http://127.0.0.1:66/admin/login",
            data: {
                "player": $("#player").val(),
                "password": $("#pwd").val()
            }
        }).then(res => {
            res.status ? $(".msg-box").removeClass("red green").addClass("green") : $(".msg-box").removeClass("red green").addClass("red")
            $(".msg-box").removeClass("hide").children(".msg").text(res.message)
        })
    })
    $(".btn-register").on("click", function() {
        $.ajax({
            method: "POST",
            url: "http://127.0.0.1:66/admin/register",
            data: {
                "player": $("#r_player").val(),
                "password": $("#r_pwd").val(),
                "password2": $("#r_pwdC").val()
            }
        }).then(res => {
            res.status ? $(".msg-box").removeClass("red green").addClass("green") : $(".msg-box").removeClass("red green").addClass("red")
            $(".msg-box").removeClass("hide").children(".msg").text(res.message)
        })
    })
    $(".msg-box span").on("click", function() {
        $(".msg-box").children(".msg").text("")
        $(".msg-box").addClass("hide").removeClass("red green")
    })
    $(".login-register").on("click", function() {
        $(".login").toggleClass("hide")
        $(".register").toggleClass("hide")
    })
})
