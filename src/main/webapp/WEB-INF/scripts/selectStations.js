$(function () {

    function alertInfo(info) {
        alert(info);
    }


    var searchButton = $("#findTrains");


    searchButton.click(function () {

        var dateFrom = new Date($("#timeFrom").val());

        var dateTo = new Date($("#timeTo").val());

        if (isNaN(dateFrom) || isNaN(dateTo)) {
            dateFrom = 0;
            dateTo = 0;
            alertInfo("Select Date");
        } else if ($("#selectStationFrom").val() === $("#selectStationTo").val()) {
            alertInfo("Select Different Stations");
        } else {
            var searchLink = "http://localhost:8080/sbb/show-routes/" + $("#selectStationFrom").val() + "/" + $("#selectStationTo").val() + "/" + dateFrom.getTime() + "/" + dateTo.getTime();
            window.location.href = searchLink;
        }
    });

});