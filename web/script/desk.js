var rootURL = "http://localhost:8080/api/desks";

var currentDesk;

var $mainArea = $('#mainArea');

function loadAllDesks() {
    console.log('loadAllDesks');
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: renderDeskList
    });
}

function findDeskById(id) {
    console.log('findDeskById: ' + id);
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function(data){
            $('#btnDelete').show();
            console.log('findDeskById success: ' + data.deskName);
            currentDesk = data;
            renderDeskDetails(currentDesk);
        }
    });
}

function createDesk() {
    console.log('createDesk');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: deskToJSON(),
        success: function(data, textStatus, jqXHR){
            alert('Record created successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error while creating record: ' + textStatus);
        }
    });
}

function updateDesk() {
    console.log('updateDesk');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: deskToJSON(),
        success: function(data, textStatus, jqXHR){
            alert('Record updated successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error while updating record: ' + textStatus);
        }
    });
}

function deleteDesk() {
    console.log('deleteDesk');
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: deskToJSON(),
        success: function(data, textStatus, jqXHR){
            alert('Record deleted successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error while deleting record: ' + textStatus);
        }
    });
}

function renderDeskList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $mainArea.find('#deskList option').remove();
    $mainArea.find('#deskList').append('<option value=0>- Select Desk -</option>');
    $.each(list, function(index, desk) {
        $mainArea.find('#deskList').append('<option value=' + desk.deskId + '>' + desk.deskName + '</option>');
    });
}

function renderDeskDetails(desk) {
    $mainArea.find('#deskId').val(desk.deskId);
    $mainArea.find('#deskName').val(desk.deskName);
    $mainArea.find('#deskMaxPersons').val(desk.deskMaxPersons);
}

function deskToJSON() {
    var deskId = $mainArea.find('#deskId').val();
    return JSON.stringify({
        "deskId": deskId == "" ? null : deskId,
        "deskName": $mainArea.find('#deskName').val(),
        "deskMaxPersons": $mainArea.find('#deskMaxPersons').val()
    });
}

function clearDeskDetails() {
    $mainArea.find('#deskId').val('');
    $mainArea.find('#deskName').val('');
    $mainArea.find('#deskMaxPersons').val('');
}

$(document).ready(function(){

    loadAllDesks();

    $mainArea.find('#btnDelete').hide();

    $mainArea.find('#btnLoad').click(function(){
        var id = $('#mainArea').find('#deskList').val();
        if (id != 0) {
            findDeskById(id);
            $('#btnDelete').show();
        }
        return false;
    });

    $mainArea.find('#btnSave').click(function(){
        if ($mainArea.find('#deskId').val() == '') {
            if ($mainArea.find('#deskName').val() == '' || $mainArea.find('#deskMaxPersons').val() == '') {
                alert('Please fill all fields');
            } else {
                createDesk();
            }
        } else {
            updateDesk();
            $('#btnDelete').hide();
        }
        clearDeskDetails();
        loadAllDesks();
        return false;
    });

    $mainArea.find('#btnDelete').click(function(){
        deleteDesk();
        $('#btnDelete').hide();
        clearDeskDetails();
        loadAllDesks();
        return false;
    });

});