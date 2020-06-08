function save() {
    var formData = new FormData($('#signupForm')[0]);
    $.ajax({
        url : "/com/poi/imports/controller/importdata",
        type: 'POST',
        data: formData,
        async: true,
        cache: false,
        contentType: false,
        processData: false,
        error : function(request) {
            parent.layer.alert("网络超时");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}