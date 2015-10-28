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

function renderClientList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $mainArea.find('#clientList option').remove();
    $mainArea.find('#clientList').append('<option value=0>- Select Client -</option>');
    $.each(list, function(index, client) {
        $mainArea.find('#clientList').append('<option value=' + client.clientId + '>' + client.clientLastName + ' ' + client.clientFirstName + '</option>');
    });
}

$(document).ready(function(){

    loadAllClients();

    loadAllDiscounts(0);

    $mainArea.find('#btnDelete').hide();

});