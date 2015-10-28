var rootURL = "http://localhost:8080/api/discounts";

var currentDiscount;

var $mainArea = $('#mainArea');

function loadAllDiscounts() {
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: renderDiscountsList
    });
}

function findDiscountById(id) {
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function(data){
            $('#btnDelete').show();
            currentDiscount = data;
            renderDiscountDetails(currentDiscount);
        }
    });
}

function createDiscount() {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: discountToJSON(),
        success: function(data, textStatus, jqXHR){
            clearDiscountDetails();
            loadAllDiscounts();
            alert('Record created successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            clearDiscountDetails();
            loadAllDiscounts();
            alert('Error while creating record: ' + textStatus);
        }
    });
}

function updateDiscount() {
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: discountToJSON(),
        success: function(data, textStatus, jqXHR){
            clearDiscountDetails();
            loadAllDiscounts();
            alert('Record updated successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            clearDiscountDetails();
            loadAllDiscounts();
            alert('Error while updating record: ' + textStatus);
        }
    });
}

function deleteDiscount() {
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: discountToJSON(),
        success: function(data, textStatus, jqXHR){
            clearDiscountDetails();
            loadAllDiscounts();
            alert('Record deleted successfully');
        },
        error: function(jqXHR, textStatus, errorThrown){
            clearDiscountDetails();
            loadAllDiscounts();
            alert('Error while deleting record: ' + textStatus);
        }
    });
}

function renderDiscountsList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $mainArea.find('#discountList option').remove();
    $mainArea.find('#discountList').append('<option value=0>- Select Discount -</option>');
    $.each(list, function(index, discount) {
        $mainArea.find('#discountList').append('<option value=' + discount.discountId + '>' + discount.discountName + '</option>');
    });
}

function renderDiscountDetails(discount) {
    $mainArea.find('#discountId').val(discount.discountId);
    $mainArea.find('#discountName').val(discount.discountName);
    $mainArea.find('#discountValue').val(discount.discountValue);
}

function discountToJSON() {
    var discountId = $mainArea.find('#discountId').val();
    return JSON.stringify({
        "discountId": discountId == "" ? null : discountId,
        "discountName": $mainArea.find('#discountName').val(),
        "discountValue": $mainArea.find('#discountValue').val()
    });
}

function clearDiscountDetails() {
    $mainArea.find('#discountId').val('');
    $mainArea.find('#discountName').val('');
    $mainArea.find('#discountValue').val('');
    $('#btnDelete').hide();
}

function selectDiscount() {
    var id = $('#mainArea').find('#discountList').val();
    if (id != 0) {
        findDiscountById(id);
        $('#btnDelete').show();
    }
}

$(document).ready(function(){

    loadAllDiscounts();

    $mainArea.find('#btnDelete').hide();

    $mainArea.find('#btnSave').click(function(){
        if ($mainArea.find('#discountName').val() == '' || $mainArea.find('#discountValue').val() == '') {
            alert('Please fill all fields');
        } else {
            if ($mainArea.find('#discountId').val() == '') {
                createDiscount();
            } else {
                updateDiscount();
            }
        }
    });

    $mainArea.find('#btnDelete').click(function(){
        deleteDiscount();
        return false;
    });

});