$(function () {


    function alertInfo(info) {
        alert(info);
    }

    var startTrainButton = $("#startTrain");

    var d = new Date($("#depaT").val());

    var trainScheduleRoute = {
        routeGroupId: $("#routeGroupIdInput").val(),
        trainId: $("#selectTrain").val(),
        routeId: $("#selectRoute").val(),
        departureTime: d,
        orderedSeats: $("#oSeats").val(),
        freeSeats: $("#fSeats").val()
    };

    startTrainButton.click(function () {


        var startLink = "http://localhost:8080/train-schedule-route/start-train";

        var checkRouteGroup = "http://localhost:8080/train-schedule-route/check-group-id/" +
            $("#routeGroupIdInput").val();


        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: checkRouteGroup,
            method: "GET"
        }).done(function (res) {
            if (!res) {
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    url: startLink,
                    method: "POST",
                    data: JSON.stringify(trainScheduleRoute)
                }).done(function (result) {
                    if (result) {
                        var container = $("#resultStr");
                        container.html("Started");
                    } else {
                        alertInfo("Train can not be started");
                    }
                });
            } else {
                alertInfo("Train Already Started")
            }
        })
    });

    var deleteButton = $("#deleteTSR");
    deleteButton.click(function () {

        var deleteLink = "http://localhost:8080/train-schedule-route/delete";

        var checkOrderedSeats = "http://localhost:8080/train-schedule-route/check-tickets/" + $("#routeGroupIdInput").val();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: checkOrderedSeats,
            method: "GET"
        }).done(function (res) {
            if (!res) {
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    url: deleteLink,
                    method: "DELETE",
                    data: JSON.stringify(trainScheduleRoute)
                });
                window.location.assign("http://localhost:8080/train-schedule-route");
            } else {
                alertInfo("There are tickets bought on this train");
            }
        })

    });
});