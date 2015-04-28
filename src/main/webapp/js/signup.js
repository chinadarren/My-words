/**
 * Created by Administrator on 2015/4/19.
 */
$(function () {
    $('#username').blur(function () {
        var username = $('#username').val();
        var hint = $('#hint');
        $.ajax({
            url: '/user?action=isUsernameExist',
            type: 'post',
            data: {'username':username},
            //dataType: 'json',
            success: function (result ){
                if (result == true ) {
                    hint.text('username is exist!');
                    hint.css('color', '#f00');
                } else {
                    hint.text('username is not exist');
                    hint.css('color', '#0f0');
                }
            },
            error: function (a, b, c) {
                alert(a.status);
            }
            //complete: function () {
            //    alert('complete...')
            //},
            //beforeSend: function () {
            //    alert('before...')
            //}
        });
    });
});
