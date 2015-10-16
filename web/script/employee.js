var rootURL = "http://localhost:8080/api/employees";

var currentEmployee;

var $mainArea = $('#mainArea');

function loadAllEmployees() {
    console.log('loadAllEmployees');
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: renderEmployeeList
    });
}

function findEmployeeById(id) {
    console.log('findEmployeedById: ' + id);
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function(data){
            $('#btnDelete').show();
            console.log('findEmployeeById success: ' + data.lastName + ' ' + data.firstName);
            currentEmployee = data;
            renderEmployeeDetails(currentEmployee);
        }
    });
}

function createEmployee() {
    console.log('createEmployee');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: employeeToJSON(),
        success: function(data, textStatus, jqXHR){
            alert('Record created successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error while creating record: ' + textStatus);
        }
    });
}

function updateEmployee() {
    console.log('updateEmployee');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: employeeToJSON(),
        success: function(data, textStatus, jqXHR){
            alert('Record updated successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error while updating record: ' + textStatus);
        }
    });
}

function deleteEmployee() {
    console.log('deleteEmployee');
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: employeeToJSON(),
        success: function(data, textStatus, jqXHR){
            alert('Record deleted successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error while deleting record: ' + textStatus);
        }
    });
}

function renderEmployeeList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $mainArea.find('#employeeList option').remove();
    $mainArea.find('#employeeList').append('<option value=0>- Select employee -</option>');
    $.each(list, function(index, employee) {
        $mainArea.find('#employeeList').append('<option value=' + employee.id + '>' + employee.lastName + ' ' + employee.firstName + '</option>');
    });
}

function renderEmployeeDetails(employee) {
    $mainArea.find('#employeeId').val(employee.id);
    $mainArea.find('#employeeFirstName').val(employee.firstName);
    $mainArea.find('#employeeLastName').val(employee.lastName);
}

function employeeToJSON() {
    var employeeId = $mainArea.find('#employeeId').val();
    return JSON.stringify({
        "id": employeeId == "" ? null : employeeId,
        "firstName": $mainArea.find('#employeeFirstName').val(),
        "lastName": $mainArea.find('#employeeLastName').val()
    });
}

function clearEmployeeDetails() {
    $mainArea.find('#employeeId').val('');
    $mainArea.find('#employeeFirstName').val('');
    $mainArea.find('#employeeLastName').val('');
}

$(document).ready(function(){

    loadAllEmployees();

    $mainArea.find('#btnDelete').hide();

    $mainArea.find('#btnLoad').click(function(){
        var id = $('#mainArea').find('#employeeList').val();
        if (id != 0) {
            findEmployeeById(id);
            $('#btnDelete').show();
        }
        return false;
    });

    $mainArea.find('#btnSave').click(function(){
        if ($mainArea.find('#employeeId').val() == '') {
            if ($mainArea.find('#employeeFirstName').val() == '' || $mainArea.find('#employeeLastName').val() == '') {
                alert('Please fill all fields');
            } else {
                createEmployee();
            }
        } else {
            updateEmployee();
            $('#btnDelete').hide();
        }
        clearEmployeeDetails();
        loadAllEmployees();
        return false;
    });

    $mainArea.find('#btnDelete').click(function(){
        deleteEmployee();
        $('#btnDelete').hide();
        clearEmployeeDetails();
        loadAllEmployees();
        return false;
    });

});