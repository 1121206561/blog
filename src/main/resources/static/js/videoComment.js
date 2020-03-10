/*js的点击事件, 转换成json格式传递给url
*  点击回复评论
* */
function videoPost() {
    /*   取值*/
    var parent_aid = $("#videoComment_hidden").val();
    var content = $("#videoComment_description").val();
    comment(parent_aid, content, 1)
}

function videoBarragePost() {
    /*   取值*/
    var parent_aid = $("#videoComment_hidden").val();
    var content = $("#videoBarrage_description").val();
    comment(parent_aid, content, 2)
}

function comment(aid, content, type) {
    /*   判断*/
    if (!content) {
        alert("回复内容不能为空~~~")
        return;
    }
    /*   赋值,跳转*/
    $.ajax({
        type: "post",
        url: "/videoComment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parent_aid": aid,
            "content": content,
            "type": type
        }),
        success: function (response) {
            /*如果发送成功就隐藏回复页面,不成功则提示框报错*/
            if (response.code == 200) {
                //隐藏该模块  一个坑:如何做到刷新并隐藏
                window.location.reload();
            } else {
                alert("亲！评论出现问题了！")
            }
        },
        dataType: "json"
    });
}


function showImg() {
    var file = document.getElementById('img_file').files[0];
    var re = new FileReader();
    re.readAsDataURL(file);
    re.onload = function (re) {
        $('#img_id').attr("src", re.target.result);
    }
}