$(document).ready(function(){

    var area = $('#leftArea');

    area.find('#entityList #employee').click(function(){
        $("#leftArea").find("#entityList li").css({'color' : '#333333'});
        $(this).css({'color' : '#FF0084'});
        $.ajax({
            type: 'POST',
            url: '/pages/admin_employee.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

    area.find('#entityList #desk').click(function(){
        $("#leftArea").find("#entityList li").css({'color' : '#333333'});
        $(this).css({'color' : '#FF0084'});
        $.ajax({
            type: 'POST',
            url: '/pages/admin_desk.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

    area.find('#entityList #dishType').click(function(){
        $("#leftArea").find("#entityList li").css({'color' : '#333333'});
        $(this).css({'color' : '#FF0084'});
        $.ajax({
            type: 'POST',
            url: '/pages/admin_dishType.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

    area.find('#entityList #dish').click(function(){
        $("#leftArea").find("#entityList li").css({'color' : '#333333'});
        $(this).css({'color' : '#FF0084'});
        $.ajax({
            type: 'POST',
            url: '/pages/admin_dish.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

    area.find('#entityList #menus').click(function(){
        $("#leftArea").find("#entityList li").css({'color' : '#333333'});
        $(this).css({'color' : '#FF0084'});
        $.ajax({
            type: 'POST',
            url: '/pages/admin_menu.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

    area.find('#entityList #discount').click(function(){
        $("#leftArea").find("#entityList li").css({'color' : '#333333'});
        $(this).css({'color' : '#FF0084'});
        $.ajax({
            type: 'POST',
            url: '/pages/admin_discount.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

    area.find('#entityList #client').click(function(){
        $("#leftArea").find("#entityList li").css({'color' : '#333333'});
        $(this).css({'color' : '#FF0084'});
        $.ajax({
            type: 'POST',
            url: '/pages/admin_client.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

});