var rootURL = "http://localhost:8080/api/menus";

var currentMenu;

var $mainArea = $('#mainArea');

function loadAllMenus() {
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: renderMenuList
    });
}

function loadAllDishes(id) {
    $mainArea.find('#menuDishList option').remove();
    $mainArea.find('#menuDishList').append('<option value=0>- Select Dish -</option>');
    $.ajax({
        type: 'GET',
        url: 'api/dishes',
        dataType: "json",
        success: function(data) {
            $.each(data, function(index, dish) {
                if (dish.dishId == id) {
                    $mainArea.find('#menuDishList').append('<option value=' + dish.dishId + ' selected>' + dish.dishName + '</option>');
                } else {
                    $mainArea.find('#menuDishList').append('<option value=' + dish.dishId + '>' + dish.dishName + '</option>');
                }
            });
        }
    });
}

function findMenuById(id) {
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function(data){
            $('#btnDelete').show();
            currentMenu = data;
            renderMenuDetails(currentMenu);
        }
    });
}

function createMenu() {
    var menuId = $mainArea.find('#menuId').val();
    var menuDishId = $mainArea.find('#menuDishList').val();
    var menuPrice = $mainArea.find('#menuPrice').val();
    $.ajax({
        type: 'GET',
        url: 'api/dishes/' + menuDishId,
        dataType: "json",
        success: function(data){
            var menu = {
                "menuId": menuId == "" ? null : menuId,
                "menuDish": data,
                "menuPrice": menuPrice
            };
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: rootURL,
                dataType: "json",
                data: JSON.stringify(menu),
                success: function(data, textStatus, jqXHR){
                    $mainArea.find('#menuList option').remove();
                    alert('Record created successfully');
                    loadAllMenus();
                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert('Error while creating record: ' + textStatus);
                }
            });
        }
    });
}

function updateMenu() {
    var menuId = $mainArea.find('#menuId').val();
    var menuDishId = $mainArea.find('#menuDishList').val();
    var menuPrice = $mainArea.find('#menuPrice').val();
    $.ajax({
        type: 'GET',
        url: 'api/dishes/' + menuDishId,
        dataType: "json",
        success: function(data){
            var menu = {
                "menuId": menuId == "" ? null : menuId,
                "menuDish": data,
                "menuPrice": menuPrice
            };
            $.ajax({
                type: 'PUT',
                contentType: 'application/json',
                url: rootURL,
                dataType: "json",
                data: JSON.stringify(menu),
                success: function(data, textStatus, jqXHR){
                    $mainArea.find('#menuList option').remove();
                    alert('Record updated successfully');
                    loadAllMenus();
                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert('Error while updating record: ' + textStatus);
                }
            });
        }
    });
}

function deleteMenu() {
    var menuId = $mainArea.find('#menuId').val();
    var menuDishId = $mainArea.find('#menuDishList').val();
    var menuPrice = $mainArea.find('#menuPrice').val();
    $.ajax({
        type: 'GET',
        url: 'api/dishes/' + menuDishId,
        dataType: "json",
        success: function(data){
            var menu = {
                "menuId": menuId == "" ? null : menuId,
                "menuDish": data,
                "menuPrice": menuPrice
            };
            $.ajax({
                type: 'DELETE',
                contentType: 'application/json',
                url: rootURL,
                dataType: "json",
                data: JSON.stringify(menu),
                success: function(data, textStatus, jqXHR){
                    $mainArea.find('#menuList option').remove();
                    alert('Record deleted successfully');
                    loadAllMenus();
                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert('Error while deleting record: ' + textStatus);
                }
            });
        }
    });
}

function renderMenuList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $mainArea.find('#menuList').append('<option value=0>- Select Menu Item -</option>');
    $.each(list, function(index, menu) {
        $mainArea.find('#menuList').append('<option value=' + menu.menuId + '>' + menu.menuDish.dishName + ' - ' + menu.menuPrice + ' UAH' + '</option>');
    });
}

function renderMenuDetails(menu) {
    loadAllDishes(menu.menuDish.dishId);
    $mainArea.find('#menuId').val(menu.menuId);
    $mainArea.find('#menuPrice').val(menu.menuPrice);
}

function clearMenuDetails() {
    loadAllDishes(0);
    $mainArea.find('#menuId').val('');
    $mainArea.find('#menuPrice').val('');
}

$(document).ready(function(){

    loadAllMenus();

    loadAllDishes(0);

    $mainArea.find('#btnDelete').hide();

    $mainArea.find('#btnLoad').click(function(){
        var id = $('#mainArea').find('#menuList').val();
        if (id != 0) {
            findMenuById(id);
            $('#btnDelete').show();
        }
        return false;
    });

    $mainArea.find('#btnSave').click(function(){
        if ($mainArea.find('#menuPrice').val() == '' || $mainArea.find('#menuDishList').val() == 0)  {
            alert('Please fill all fields');
        } else {
            if ($mainArea.find('#menuId').val() == '') {
                createMenu();
            } else {
                updateMenu();
            }
        }
        clearMenuDetails();
        return false;
    });

    $mainArea.find('#btnDelete').click(function(){
        deleteMenu();
        $('#btnDelete').hide();
        clearMenuDetails();
        return false;
    });

});