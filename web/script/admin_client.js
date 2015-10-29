var rootURL = "http://localhost:8080/api/clients";

var currentClient;

var $mainArea = $('#mainArea');

function loadAllClients() {
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: renderClientList
    });
}

function loadAllDiscounts(id) {
    $mainArea.find('#discountList option').remove();
    $mainArea.find('#discountList').append('<option value=0>- Select Discount -</option>');
    $.ajax({
        type: 'GET',
        url: 'api/discounts',
        dataType: "json",
        success: function(data) {
            $.each(data, function(index, discount) {
                if (discount.discountId == id) {
                    $mainArea.find('#discountList').append('<option value=' + discount.discountId + ' selected>' + discount.discountName + '</option>');
                } else {
                    $mainArea.find('#discountList').append('<option value=' + discount.discountId + '>' + discount.discountName + '</option>');
                }
            });
        }
    });
}

function findClientById(id) {
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function(data){
            $('#btnDelete').show();
            currentClient = data;
            renderClientDetails(currentClient);
        }
    });
}

function createClient() {
    var clientId = $mainArea.find('#clientId').val();
    var clientDiscountId = $mainArea.find('#discountList').val();
    var clientFirstName = $mainArea.find('#clientFirstName').val();
    var clientLastName = $mainArea.find('#clientLastName').val();
    $.ajax({
        type: 'GET',
        url: 'api/discounts/' + clientDiscountId,
        dataType: "json",
        success: function(data){
            var discount = {
                "clientId": clientId == "" ? null : clientId,
                "clientFirstName": clientFirstName,
                "clientLastName": clientLastName,
                "clientDiscount": data
            };
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: rootURL,
                dataType: "json",
                data: JSON.stringify(discount),
                success: function(data, textStatus, jqXHR){
                    clearClientDetails();
                    loadAllClients();
                    alert('Record created successfully');
                },
                error: function(jqXHR, textStatus, errorThrown){
                    clearClientDetails();
                    loadAllClients();
                    alert('Error while creating record: ' + textStatus);
                }
            });
        }
    });
}

function updateClient() {
    var clientId = $mainArea.find('#clientId').val();
    var clientDiscountId = $mainArea.find('#discountList').val();
    var clientFirstName = $mainArea.find('#clientFirstName').val();
    var clientLastName = $mainArea.find('#clientLastName').val();
    $.ajax({
        type: 'GET',
        url: 'api/discounts/' + clientDiscountId,
        dataType: "json",
        success: function(data){
            var discount = {
                "clientId": clientId == "" ? null : clientId,
                "clientFirstName": clientFirstName,
                "clientLastName": clientLastName,
                "clientDiscount": data
            };
            $.ajax({
                type: 'PUT',
                contentType: 'application/json',
                url: rootURL,
                dataType: "json",
                data: JSON.stringify(discount),
                success: function(data, textStatus, jqXHR){
                    clearClientDetails();
                    loadAllClients();
                    alert('Record updated successfully');
                },
                error: function(jqXHR, textStatus, errorThrown){
                    clearClientDetails();
                    loadAllClients();
                    alert('Error while updating record: ' + textStatus);
                }
            });
        }
    });
}

function deleteClient() {
    var clientId = $mainArea.find('#clientId').val();
    var clientDiscountId = $mainArea.find('#discountList').val();
    var clientFirstName = $mainArea.find('#clientFirstName').val();
    var clientLastName = $mainArea.find('#clientLastName').val();
    $.ajax({
        type: 'GET',
        url: 'api/discounts/' + clientDiscountId,
        dataType: "json",
        success: function(data){
            var discount = {
                "clientId": clientId == "" ? null : clientId,
                "clientFirstName": clientFirstName,
                "clientLastName": clientLastName,
                "clientDiscount": data
            };
            $.ajax({
                type: 'DELETE',
                contentType: 'application/json',
                url: rootURL,
                dataType: "json",
                data: JSON.stringify(discount),
                success: function(data, textStatus, jqXHR){
                    clearClientDetails();
                    loadAllClients();
                    alert('Record deleted successfully');
                },
                error: function(jqXHR, textStatus, errorThrown){
                    clearClientDetails();
                    loadAllClients();
                    alert('Error while deleting record: ' + textStatus);
                }
            });
        }
    });
}

function renderClientList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $mainArea.find('#clientList option').remove();
    $mainArea.find('#clientList').append('<option value=0>- Select Client -</option>');
    $.each(list, function(index, client) {
        $mainArea.find('#clientList').append('<option value=' + client.clientId + '>' + client.clientLastName + ' ' + client.clientFirstName + '</option>');
    });
}

function renderClientDetails(client) {
    loadAllDiscounts(client.clientDiscount.discountId);
    $mainArea.find('#clientId').val(client.clientId);
    $mainArea.find('#clientFirstName').val(client.clientFirstName);
    $mainArea.find('#clientLastName').val(client.clientLastName);
}

function clearClientDetails() {
    loadAllDiscounts(0);
    $mainArea.find('#clientId').val('');
    $mainArea.find('#clientFirstName').val('');
    $mainArea.find('#clientLastName').val('');
    $('#btnDelete').hide();
}

function selectClient() {
    var id = $('#mainArea').find('#clientList').val();
    if (id != 0) {
        findClientById(id);
        $('#btnDelete').show();
    }
}

$(document).ready(function(){

    loadAllClients();

    loadAllDiscounts(0);

    $mainArea.find('#btnDelete').hide();

    $mainArea.find('#btnSave').click(function(){
        if ($mainArea.find('#clientFirstName').val() == '' || $mainArea.find('#discountList').val() == 0 || $mainArea.find('#clientFirstName').val() == '')  {
            alert('Please fill all fields');
        } else {
            if ($mainArea.find('#clientId').val() == '') {
                createClient();
            } else {
                updateClient();
            }
        }
        return false;
    });

    $mainArea.find('#btnDelete').click(function(){
        deleteClient();
        clearClientDetails();
        loadAllClients();
        return false;
    });

});