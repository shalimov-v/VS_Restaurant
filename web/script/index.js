$(document).ready(function(){

    var $menu = $('#menu');

    $.ajax({
        type: "POST",
        url: "/pages/homepage_content.html",
        cache: false,
        success: function(html){
            $("#content").html(html);
        }
    });

    $menu.find('#menuItems #homepage').click(function(){
        $('#menu').find('#menuItems li').css({'background': 'none'});
        $(this).css({'background': 'url(../images/img03.gif) no-repeat center bottom'});
        $.ajax({
            type: "POST",
            url: "/pages/homepage_content.html",
            cache: false,
            success: function(html){
                $("#content").html(html);
            }
        });
    });

    $menu.find('#menuItems #menus').click(function(){
        $('#menu').find('#menuItems li').css({'background': 'none'});
        $(this).css({'background': 'url(../images/img03.gif) no-repeat center bottom'});
        $.ajax({
            type: "POST",
            url: "/pages/menu_content.html",
            cache: false,
            success: function(html){
                $("#content").html(html);
            }
        });
    });

    $menu.find('#menuItems #reserve').click(function(){
        $('#menu').find('#menuItems li').css({'background': 'none'});
        $(this).css({'background': 'url(../images/img03.gif) no-repeat center bottom'});
        $.ajax({
            type: "POST",
            url: "/pages/reserve_content.html",
            cache: false,
            success: function(html){
                $("#content").html(html);
            }
        });
    });

    $menu.find('#menuItems #contacts').click(function(){
        $('#menu').find('#menuItems li').css({'background': 'none'});
        $(this).css({'background': 'url(../images/img03.gif) no-repeat center bottom'});
        $.ajax({
            type: "POST",
            url: "/pages/contact_content.html",
            cache: false,
            success: function(html){
                $("#content").html(html);
            }
        });
    });

    $menu.find('#menuItems #admin').click(function(){
        $('#menu').find('#menuItems li').css({'background': 'none'});
        $(this).css({'background': 'url(../images/img03.gif) no-repeat center bottom'});
        $.ajax({
            type: "POST",
            url: "/pages/admin_content.html",
            cache: false,
            success: function(html){
                $("#content").html(html);
            }
        });
    });

});