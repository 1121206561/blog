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
                window.location.reload();
            } else {
                /*如果他是登录报错,就给一个是否登录的提示,如果是则进行登录*/
                if (response.code == 2003) {
                    var isAcception = confirm(response.message);
                    if (isAcception) {
                        window.open("https://github.com/login/oauth/authorize?client_id=1885fcec32c1656b5e00&redirect_uri=http://175.24.14.146/callback&scope=user&state=1");
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
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        //判断它的子元素是否有值,如果有则不进行查询
        //没有就打开
        var subCommentContainer = $('#comment-' + id);
        if (subCommentContainer.children().length != 1) {
            comments.addClass("in");
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comments/" + id, function (data) {
                    $.each(data.data.reverse(), function (index, comment) {
                        //字符串拼接
                        var mediaLeftElement = $("<div/>", {
                            "class": "media-left"
                        }).append($("<img/>", {
                            "class": "media-object img-rounded",
                            "src": comment.user.avatar_url
                        }));
                        var mediaBodyElement = $("<div/>", {
                            "class": "media-body"
                        }).append($("<h6/>", {
                            "class": "media-heading",
                            "html": comment.commentator
                        })).append($("<div/>", {
                            "html": comment.content
                        })).append($("<div/>", {
                                "class": "menu"
                            }).append($("<span/>", {
                                "class": "pull-right",
                                "html": moment(comment.gmtCreator).format("YYYY-MM-DD")
                            }))
                        );

                        var mediaElement = $("<div/>", {
                            "class": "media",
                        }).append(mediaLeftElement)
                            .append(mediaBodyElement);

                        var commentElement = $("<div/>", {
                            "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-sp comments",
                        }).append(mediaElement);
                        subCommentContainer.prepend(commentElement);
                    });
                    comments.addClass("in");
                    e.setAttribute("data-collapse", "in");
                    e.classList.add("active");
                }
            );
        }
    }
}