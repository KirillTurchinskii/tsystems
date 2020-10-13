$(function () {


    function alertInfo(info) {
        alert(info);
    }

    var button = $("#createButton");

    button.click(function () {

        var postlink = "http://localhost:8080/train-schedule-route/create-train-schedule-route";
        // var checkTimeLink = "http://localhost:8080/route-details/check-time";


        var trainScheduleRoute = {
            trainId: $("#selectTrain").val(),
            routeId: $("#selectRoute").val(),
            departureTime: $("#timeInput").val(),
            orderedSeats: 0,
            freeSeats: 0
        }

        // function checkTime() {
        //     return $.ajax({
        //         headers: {
        //             'Accept': 'application/json',
        //             'Content-Type': 'application/json'
        //         },
        //         url: checkTimeLink,
        //         method: "GET",
        //         data: JSON.stringify(scheduleTrainRoute)
        //     });
        // }

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: postlink,
            method: "POST",
            data: JSON.stringify(trainScheduleRoute)
        }).done(function () {
            // alertInfo(JSON.stringify(res));
            var container = $("#resultStr");
            // container.style.color = "green";
            container.html("Added");
        });
    });
});