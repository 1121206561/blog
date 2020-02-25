/*js的点击事件, 转换成json格式传递给url*/
function post() {
    /*   取值*/
    var parent_id = $("#comment_hidden").val();
    var content = $("#comment_description").val();
    /*   赋值,跳转*/
    $.ajax({
        type: "post",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parent_id": parent_id,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            /*如果发送成功就隐藏回复页面,不成功则提示框报错*/
            if (response.code == 200) {
                $("#comment_section").hide();
            } else {
                /*如果他是登录报错,就给一个是否登录的提示,如果是则进行登录*/
                if (response.code = 2003) {
                    var isAcception = confirm(response.message);
                    if (isAcception) {
                        window.open("https://github.com/login/oauth/authorize?client_id=1885fcec32c1656b5e00&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        /*给跳转回去的页面设置一个值来进行判断 */
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message)
                }
            }
        },
        dataType: "json"
    });
}