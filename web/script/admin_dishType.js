var rootURL = "http://localhost:8080/api/dishTypes";

var currentDishType;

var $mainArea = $('#mainArea');

function loadAllDishTypes() {
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: renderDishTypeList
    });
}

function findDishTypeById(id) {
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function(data){
            $('#btnDelete').show();
            currentDishType = data;
            renderDishTypeDetails(currentDishType);
        }
    });
}

function createDishType() {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: dishTypeToJSON(),
        success: function(data, textStatus, jqXHR){
            alert('Record created successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error while creating record: ' + textStatus);
        }
    });
}

function updateDishType() {
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: dishTypeToJSON(),
        success: function(data, textStatus, jqXHR){
            alert('Record updated successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error while updating record: ' + textStatus);
        }
    });
}

function deleteDishType() {
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: dishTypeToJSON(),
        success: function(data, textStatus, jqXHR){
            alert('Record deleted successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error while deleting record: ' + textStatus);
        }
    });
}

function renderDishTypeList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $mainArea.find('#dishTypeList option').remove();
    $mainArea.find('#dishTypeList').append('<option value=0>- Select Dish Type -</option>');
    $.each(list, function(index, dishType) {
        $mainArea.find('#dishTypeList').append('<option value=' + dishType.dishTypeId + '>' + dishType.dishTypeName + '</option>');
    });
}

function renderDishTypeDetails(dishType) {
    $mainArea.find('#dishTypeId').val(dishType.dishTypeId);
    $mainArea.find('#dishTypeName').val(dishType.dishTypeName);
}

function dishTypeToJSON() {
    var dishTypeId = $mainArea.find('#dishTypeId').val();
    return JSON.stringify({
        "dishTypeId": dishTypeId == "" ? null : dishTypeId,
        "dishTypeName": $mainArea.find('#dishTypeName').val()
    });
}

function clearDishTypeDetails() {
    $mainArea.find('#dishTypeId').val('');
    $mainArea.find('#dishTypeName').val('');
}

function selectDishType() {
    var id = $('#mainArea').find('#dishTypeList').val();
    if (id != 0) {
        findDishTypeById(id);
        $('#btnDelete').show();
    }
}

$(document).ready(function(){

    loadAllDishTypes();

    $mainArea.find('#btnDelete').hide();

    $mainArea.find('#btnSave').click(function(){
        if ($mainArea.find('#dishTypeId').val() == '') {
            if ($mainArea.find('#dishTypeName').val() == '') {
                alert('Please fill all fields');
            } else {
                createDishType();
            }
        } else {
            updateDishType();
            $('#btnDelete').hide();
        }
        clearDishTypeDetails();
        loadAllDishTypes();
        return false;
    });

    $mainArea.find('#btnDelete').click(function(){
        deleteDishType();
        $('#btnDelete').hide();
        clearDishTypeDetails();
        loadAllDishTypes();
        return false;
    });

});