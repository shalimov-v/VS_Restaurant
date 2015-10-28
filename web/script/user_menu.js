var $leftArea =  $('#leftArea');
var $mainArea =  $('#mainArea');
var currentMenuDish;

function fillDishTypes() {
    $.ajax({
        type: 'GET',
        url: '/api/dishTypes',
        dataType: "json",
        success: function(data){
            var list = data == null ? [] : (data instanceof Array ? data : [data]);
            $leftArea.find('#dishTypeList option').remove();
            $leftArea.find('#dishTypeList').append('<option value=0>- Select Dish Type -</option>');
            $.each(list, function(index, dishType) {
                $leftArea.find('#dishTypeList').append('<option value=' + dishType.dishTypeId + '>' + dishType.dishTypeName + '</option>');
            });
        }
    });
}

function selectMenuDishType() {
    var dishTypeId = $leftArea.find('#dishTypeList').val();
    $.ajax({
        type: 'GET',
        url: '/api/menus/type/' + dishTypeId,
        dataType: "json",
        success: function(data){
            var list = data == null ? [] : (data instanceof Array ? data : [data]);
            $leftArea.find('#menuDishesList option').remove();
            $leftArea.find('#menuDishesList').append('<option value=0>- Select Dish -</option>');
            $.each(list, function(index, menu) {
                $leftArea.find('#menuDishesList').append('<option value=' + menu.menuId + '>' + menu.menuDish.dishName + '</option>');
            });
            $leftArea.find('#menuDishes').show();
        }
    });
}

function selectMenuDish() {
    var dishId = $leftArea.find('#menuDishesList').val();
    console.log(dishId);
    $.ajax({
        type: 'GET',
        url: '/api/menus/' + dishId,
        dataType: "json",
        success: function(data){
            currentMenuDish = data;
            $mainArea.find('#menuDishName').text(data.menuDish.dishName);
            $mainArea.find('#menuDishDescription').text(data.menuDish.dishDescription);
            $mainArea.find('#menuDishPrice').text(data.menuPrice + ' UAH');
            $mainArea.show();
        }
    });
}



$(document).ready(function(){

    $leftArea.find('#menuDishes').hide();

    $mainArea.hide();

    fillDishTypes();

});