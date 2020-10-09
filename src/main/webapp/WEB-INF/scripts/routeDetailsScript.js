$(function () {

    function alertInfo(info) {
        alert(info);
    }

    $(document).ready(function () {
        if ($("#newSelectStation option").length === 0) {
            $("#newSelectStation").attr("disabled", true);
            $("#newKmInput").attr("disabled", true);
            $("#newStationTypeInput").attr("disabled", true);
            $("#addDetails").attr("disabled", true);

        }
    });


    var addDetailsButton = $("#addDetails");

    addDetailsButton.click(function () {


            var routeDetails = {
                routeId: $("#routeIdHidden").val(),
                stationId: $("#newSelectStation").val(),
                // stationOrder: $("#newStationOrderInput").val(),
                km: $("#newKmInput").val(),
                type: $("#newStationTypeInput").val()
            };

            var postlink = "http://localhost:8080/route-details/add-route-details-part";

            var checkMaxKm = "http://localhost:8080/route-details/" + $("#routeIdHidden").val() + "/" + $("#newKmInput").val() + "/check-km"

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
                    }).done(function (result) {

                        $("#newSelectStation option[value=" + result.stationId + "]").remove();

                        if ($("#newSelectStation option").length === 0) {
                            $("#newSelectStation").attr("disabled", true);
                            $("#newKmInput").attr("disabled", true);
                            $("#newStationTypeInput").attr("disabled", true);
                            $("#addDetails").attr("disabled", true);

                        }


                        var container = $("#tableContainer");
                        container.addClass("test-class");
                        var routeDetailsElement = $("<div></div>");
                        routeDetailsElement.addClass("form-row my-sm-2");

                        var stationName = $("<div></div>");
                        stationName.addClass("input-group col-md-2");
                        var stationNameLabelDiv = $("<div></div>");
                        stationNameLabelDiv.addClass("input-group-prepend");
                        var labelForName = $("<label>Station</label>")
                        labelForName.addClass("input-group-text");
                        var inputForName = $("<input type='text' disabled>");
                        inputForName.addClass("form-control");
                        inputForName.val(result.refStationEntity.name);
                        labelForName.appendTo(stationNameLabelDiv);
                        inputForName.appendTo(stationNameLabelDiv);
                        stationNameLabelDiv.appendTo(stationName);
                        stationName.appendTo(routeDetailsElement);

                        // var stationName = $("<div></div>");
                        // stationName.addClass("input-group col-md-2");
                        // var stationNameLabelDiv = $("<div></div>");
                        // stationNameLabelDiv.addClass("input-group-prepend");
                        // var labelForName = $("<label>Station</label>")
                        // labelForName.addClass("input-group-text");
                        // var selectForName = $("<select disabled></select>")
                        // selectForName.addClass("custom-select");
                        // var optionforName = $("<option>"+result.refStationEntity.name+"</option>");
                        // optionforName.val(result.routeId);
                        // optionforName.appendTo(selectForName);
                        // labelForName.appendTo(stationNameLabelDiv);
                        // selectForName.appendTo(stationNameLabelDiv);
                        // stationNameLabelDiv.appendTo(stationName);
                        // stationName.appendTo(routeDetailsElement);


                        var stationOrder = $("<div></div>");
                        stationOrder.addClass("input-group col-md-2 offset-xs-1");
                        var stationOrderLabelDiv = $("<div></div>");
                        stationOrderLabelDiv.addClass("input-group-prepend");
                        var labelForOrder = $("<label>Order</label>")
                        labelForOrder.addClass("input-group-text");
                        var inputForOrder = $("<input type='number' disabled>");
                        inputForOrder.addClass("form-control");
                        inputForOrder.val(result.stationOrder);
                        labelForOrder.appendTo(stationOrderLabelDiv);
                        inputForOrder.appendTo(stationOrderLabelDiv);
                        stationOrderLabelDiv.appendTo(stationOrder);
                        stationOrder.appendTo(routeDetailsElement);


                        var stationKm = $("<div></div>");
                        stationKm.addClass("input-group col-md-2 offset-xs-1");
                        var stationKmLabelDiv = $("<div></div>");
                        stationKmLabelDiv.addClass("input-group-prepend");
                        var labelForKm = $("<label>Km</label>")
                        labelForKm.addClass("input-group-text");
                        var inputForKm = $("<input type='number' disabled>");
                        inputForKm.addClass("form-control");
                        inputForKm.val(result.km);
                        labelForKm.appendTo(stationKmLabelDiv);
                        inputForKm.appendTo(stationKmLabelDiv);
                        stationKmLabelDiv.appendTo(stationKm);
                        stationKm.appendTo(routeDetailsElement);


                        var stationType = $("<div></div>");
                        stationType.addClass("input-group col-md-2 offset-xs-1");
                        var stationTypeLabelDiv = $("<div></div>");
                        stationTypeLabelDiv.addClass("input-group-prepend");
                        var labelForType = $("<label>Type</label>")
                        labelForType.addClass("input-group-text");
                        var inputForType = $("<input type='number' disabled>");
                        inputForType.addClass("form-control");
                        inputForType.val(result.type);
                        labelForType.appendTo(stationTypeLabelDiv);
                        inputForType.appendTo(stationTypeLabelDiv);
                        stationTypeLabelDiv.appendTo(stationType);
                        stationType.appendTo(routeDetailsElement);


                        // var updateRef = "@/route-details/" + result.routeId + "/" + result.stationId + "/" + result.stationOrder + "/update";
                        var updateRef = result.stationId + "/" + result.stationOrder + "/update";
                        // alertInfo(updateRef);
                        var buttonUpdate = $("<a role='button' href=" + updateRef + ">Update this route details</a>");
                        buttonUpdate.addClass("btn btn-primary");
                        buttonUpdate.appendTo(routeDetailsElement);

                        var deleteRef = result.stationId + '/' + result.stationOrder + '/delete';
                        var buttonDelete = $("<a role='button'  href=" + deleteRef + ">Delete this route details</a>");
                        buttonDelete.addClass("btn btn-danger");
                        buttonDelete.appendTo(routeDetailsElement);


                        routeDetailsElement.appendTo(container);
                    });
                    // } else {
                    //     alertInfo("No more stations Left");
                    // }
                } else {
                    alertInfo("Km is lower or equals to last station's km")
                }
            }).fail(function (error) {
                alertInfo("Km calc problem");
            });
        }
    );
});