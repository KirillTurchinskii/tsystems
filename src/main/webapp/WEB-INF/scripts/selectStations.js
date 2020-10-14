$(function () {

    function alertInfo(info) {
        alert(info);
    }


    var searchButton = $("#findTrains");


    searchButton.click(function () {

        var dateFrom = new Date($("#timeFrom").val());

        var dateTo = new Date($("#timeTo").val());

        var providedData = {
            stationIdFrom: $("#selectStationFrom").val(),
            stationIdTo: $("#selectStationTo").val(),
            timeFrom: dateFrom,
            timeTo: dateTo
        }

        var searchLink = "http://localhost:8080/sbb/show-routes";
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: searchLink,
            method: "POST",
            data: JSON.stringify(providedData)
        }).done(function (result) {

        });

    });

});