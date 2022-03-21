let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
    },

    save: function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
//        console.log(data);
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response){
//            console.log(response);
            if(response.status === 500) {
                alert("회원가입을 실패했습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href="/";
            }
        }).fail(function(error){
//            alert(JSON.stringify(error));
        });
    },
    update: function () {
            let data = {
                id: $("#id").val(),
                username: $("#username").val(),
                password: $("#password").val(),
                email: $("#email").val()
            }

            $.ajax({
                type: "PUT",
                url: "/user",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function (res) {
                alert("회원수정이 완료되었습니다.");
                location.href = "/";
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
}

index.init();

//$("#btn-login").on("click", () => {
//            this.login();
//});
//login: function() {
//            let data = {
//                username: $("#username").val(),
//                password: $("#password").val(),
//            };
//
//            $.ajax({
//                type: "POST",
//                url: "/api/login",
//                data: JSON.stringify(data),
//                contentType: "application/json; charset=utf-8",
//                dataType: "json"
//            }).done(function(response){
//    //            console.log(response);
//                alert("로그인이 완료되었습니다.");
//                location.href="/";
//            }).fail(function(error){
//    //            alert(JSON.stringify(error));
//            });
//}