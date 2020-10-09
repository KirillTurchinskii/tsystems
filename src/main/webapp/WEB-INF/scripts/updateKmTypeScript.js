$(function () {

    function alertInfo(info) {
        alert(info);
    }

    // $(document).ready(function (){
    //     if ($("#newSelectStation option").length === 0) {
    //         $("#newSelectStation").attr("disabled", true);
    //         $("#newKmInput").attr("disabled", true);
    //         $("#newStationTypeInput").attr("disabled", true);
    //         $("#addDetails").attr("disabled", true);
    //
    //     }
    // });


    var changeDetails = $("#changeDetails");

    changeDetails.click(function () {


            var routeDetails = {
                routeId: $("#routeIdHidden").val(),
                stationId: $("#stationIdHidden").val(),
                stationOrder: $("#stationOrderInput").val(),
                km: $("#kmInput").val(),
                type: $("#stationTypeInput").val()
            };

            var postlink = "http://localhost:8080/route-details/" + $("#routeIdHidden").val() + "/" + $("#stationIdHidden").val() + "/" + $("#stationOrderInput").val();

            var checkMaxKm = "http://localhost:8080/route-details/" + $("#routeIdHidden").val() + "/" + $("#stationOrderInput").val() + "/" + $("#kmInput").val() + "/check-km"

            function checkKm() {
                return $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    url: checkMaxKm,
                    method: "GET"
                });
            }

            checkKm().done(function (kmr) {
                if (kmr) {

                    $.ajax({
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        url: postlink,
                        method: "POST",
                        data: JSON.stringify(routeDetails)
                    }).done(function (res) {
                        if (res) {
                            var container = $("#resultStr");
                            container.style = "color:green";
                            container.html("Updated");
                        } else {
                            alertInfo("New object doesnt equals to saved");
                        }

                        //redirect
                    });
                } else {
                    alertInfo("Km is lower then prev station's km or higher then next")
                }
            }).fail(function (error) {
                alertInfo("Km calc problem");
            });
        }
    );
});