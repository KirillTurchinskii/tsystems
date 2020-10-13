$(function () {


    function alertInfo(info) {
        alert(info);
    }


    var startTrainButton = $("#startTrain");


    var d = new Date($("#depaT").val());

    // $("timeInput").value = $("#depaT");

    startTrainButton.click(function () {

        var trainScheduleRoute = {
            trainId: $("#selectTrain").val(),
            routeId: $("#selectRoute").val(),
            departureTime: d,
            orderedSeats: $("#oSeats").val(),
            freeSeats: $("#fSeats").val()
        };

        var startLink = "http://localhost:8080/train-schedule-route/start-train";

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
                container.style.color = "green";
                container.html("Started");
            } else {
                alertInfo("Train can not be started");
            }
        });
    });

    var deleteButton = $("#deleteTSR");
    deleteButton.click(function () {

        var deleteLink = "http://localhost:8080/train-schedule-route/delete";

        // var testLink = "http://localhost:8080/train-schedule-route/delete";

        var trainRoute = {
            trainId: $("#selectTrain").val(),
            routeId: $("#selectRoute").val(),
            departureTime: d,
            orderedSeats: $("#oSeats").val(),
            freeSeats: $("#fSeats").val()
        }

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: deleteLink,
            method: "POST",
            data: JSON.stringify(trainRoute)
        });

        window.location.href = "http://localhost:8080/train-schedule-route";
    });
});