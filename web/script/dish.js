var rootURL = "http://localhost:8080/api/dishes";

var currentDish;

var $mainArea = $('#mainArea');

function loadAllDishes() {
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: renderDishList
    });
}

function loadAllDishTypes(id) {
    $mainArea.find('#dishTypeList option').remove();
    $mainArea.find('#dishTypeList').append('<option value=0>- Select Dish Type -</option>');
    $.ajax({
        type: 'GET',
        url: 'api/dishTypes',
        dataType: "json",
        success: function(data) {
            $.each(data, function(index, dishType) {
                if (dishType.dishTypeId == id) {
                    $mainArea.find('#dishTypeList').append('<option value=' + dishType.dishTypeId + ' selected>' + dishType.dishTypeName + '</option>');
                } else {
                    $mainArea.find('#dishTypeList').append('<option value=' + dishType.dishTypeId + '>' + dishType.dishTypeName + '</option>');
                }
            });
        }
    });
}

function findDishById(id) {
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function(data){
            $('#btnDelete').show();
            currentDish = data;
            renderDishDetails(currentDish);
        }
    });
}

function createDish() {
    var dishId = $mainArea.find('#dishId').val();
    var dishTypeId = $mainArea.find('#dishTypeList').val();
    var dishName = $mainArea.find('#dishName').val();
    var dishDescription = $mainArea.find('#dishDescription').val();
    $.ajax({
        type: 'GET',
        url: 'api/dishTypes/' + dishTypeId,
        dataType: "json",
        success: function(data){
            var dish = {
                "dishId": dishId == "" ? null : dishId,
                "dishName": dishName,
                "dishType": data,
                "dishDescription": dishDescription
            };
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: rootURL,
                dataType: "json",
                data: JSON.stringify(dish),
                success: function(data, textStatus, jqXHR){
                    alert('Record created successfully');
                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert('Error while creating record: ' + textStatus);
                }
            });
        }
    });
}

function updateDish() {
    var dishId = $mainArea.find('#dishId').val();
    var dishTypeId = $mainArea.find('#dishTypeList').val();
    var dishName = $mainArea.find('#dishName').val();
    var dishDescription = $mainArea.find('#dishDescription').val();
    $.ajax({
        type: 'GET',
        url: 'api/dishTypes/' + dishTypeId,
        dataType: "json",
        success: function(data){
            var dish = {
                "dishId": dishId == "" ? null : dishId,
                "dishName": dishName,
                "dishType": data,
                "dishDescription": dishDescription
            };
            $.ajax({
                type: 'PUT',
                contentType: 'application/json',
                url: rootURL,
                dataType: "json",
                data: JSON.stringify(dish),
                success: function(data, textStatus, jqXHR){
                    alert('Record updated successfully');
                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert('Error while updating record: ' + textStatus);
                }
            });
        }
    });
}

function renderDishList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $mainArea.find('#dishList option').remove();
    $mainArea.find('#dishList').append('<option value=0>- Select Dish -</option>');
    $.each(list, function(index, dish) {
        $mainArea.find('#dishList').append('<option value=' + dish.dishId + '>' + dish.dishName + '</option>');
    });
}

function renderDishDetails(dish) {
    loadAllDishTypes(dish.dishType.dishTypeId);
    $mainArea.find('#dishId').val(dish.dishId);
    $mainArea.find('#dishName').val(dish.dishName);
    $mainArea.find('#dishDescription').val(dish.dishDescription);
}

function clearDishDetails() {
    loadAllDishTypes(0);
    $mainArea.find('#dishId').val('');
    $mainArea.find('#dishName').val('');
    $mainArea.find('#dishDescription').val('');
}

$(document).ready(function(){

    loadAllDishes();

    loadAllDishTypes(0);

    $mainArea.find('#btnDelete').hide();

    $mainArea.find('#btnLoad').click(function(){
        var id = $('#mainArea').find('#dishList').val();
        if (id != 0) {
            findDishById(id);
            $('#btnDelete').show();
        }
        return false;
    });

    $mainArea.find('#btnSave').click(function(){
        if ($mainArea.find('#dishName').val() == '' || $mainArea.find('#dishTypeList').val() == 0)  {
            alert('Please fill all fields');
        } else {
            if ($mainArea.find('#dishId').val() == '') {
                createDish();
            } else {
                updateDish();
            }
            clearDishDetails();
            loadAllDishes();
        }
    });

});