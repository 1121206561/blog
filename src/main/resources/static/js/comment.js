/*js的点击事件, 转换成json格式传递给url
*  点击回复评论
* */
function post() {
    /*   取值*/
    var parent_id = $("#comment_hidden").val();
    var content = $("#comment_description").val();
    comment(parent_id, content, 1)
}

function comment(id, content, type) {
    /*   判断*/
    if (!content) {
        alert("回复内容不能为空~~~")
        return;
    }
    /*   赋值,跳转*/
    $.ajax({
        type: "post",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parent_id": id,
            "content": content,
            "type": type
        }),
        success: function (response) {
            /*如果发送成功就隐藏回复页面,不成功则提示框报错*/
            if (response.code == 200) {
                //隐藏该模块  一个坑:如何做到刷新并隐藏
                $("#comment_section").hide();
            } else {
                /*如果他是登录报错,就给一个是否登录的提示,如果是则进行登录*/
                if (response.code == 2003) {
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

function commentTarger(e) {
    var id = e.getAttribute("data-id");
    var content = $("#input-" + id).val();
    comment(id, content, 2)
}

//二级评论
function collapseComments(e) {
    //获取传递过来的值
    var id = e.getAttribute("data-id")
    //拿到需要操作的模块的id
    var comments = $("#comment-" + id);
    //判断是折叠还是打开
    var collapse = e.getAttribute("data-collapse")
    //如果有则折叠
    if (collapse) {
        //每次展示都会重新生成一个<div>所以每次折叠起来都要移除掉已经生成的
        $("#qqq").remove();
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        //没有就打开
        $.getJSON("/comments/" + id, function (data) {
            var subCommentContainer = $('#comment-' + id);
            $.each(data.data, function (index, comment) {
                var c = $("<div/>", {
                    "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-sp comments",
                    "id": "qqq",
                    html: comment.content
                });
                subCommentContainer.prepend(c);
            });
            comments.addClass("in");
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        });
    }
}
