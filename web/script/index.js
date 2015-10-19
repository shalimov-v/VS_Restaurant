$(document).ready(function(){

    var area = $('#leftArea');

    area.find('#entityList #employee').click(function(){
        $("#leftArea").find("#entityList li")
            .css({'border' : 'solid #dddddd 1px'})
            .css({'color' : '#0063DC'});

        $(this)
            .css({'color' : '#FF0084'})
            .css({'border' : 'none'});
        $.ajax({
            type: 'POST',
            url: '/pages/employee.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

    area.find('#entityList #desk').click(function(){
        $("#leftArea").find("#entityList li")
            .css({'border' : 'solid #dddddd 1px'})
            .css({'color' : '#0063DC'});

        $(this)
            .css({'color' : '#FF0084'})
            .css({'border' : 'none'});
        $.ajax({
            type: 'POST',
            url: '/pages/desk.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

    area.find('#entityList #dishType').click(function(){
        $("#leftArea").find("#entityList li")
            .css({'border' : 'solid #dddddd 1px'})
            .css({'color' : '#0063DC'});

        $(this)
            .css({'color' : '#FF0084'})
            .css({'border' : 'none'});
        $.ajax({
            type: 'POST',
            url: '/pages/dishType.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

    area.find('#entityList #dish').click(function(){
        $("#leftArea").find("#entityList li")
            .css({'border' : 'solid #dddddd 1px'})
            .css({'color' : '#0063DC'});

        $(this)
            .css({'color' : '#FF0084'})
            .css({'border' : 'none'});
        $.ajax({
            type: 'POST',
            url: '/pages/dish.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

    area.find('#entityList #menu').click(function(){
        $("#leftArea").find("#entityList li")
            .css({'border' : 'solid #dddddd 1px'})
            .css({'color' : '#0063DC'});

        $(this)
            .css({'color' : '#FF0084'})
            .css({'border' : 'none'});
        $.ajax({
            type: 'POST',
            url: '/pages/menu.html',
            cache: false,
            success: function(html){
                $('#mainArea').html(html);
            }
        });
    });

});