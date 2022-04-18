$(function () {
    var deleteRouteButton = $("#deleteRouteButton");

    var deleteLink = "";

    var checkIfNotUsed = "";

    deleteRouteButton.click(function () {

        function checkIfRouteNotUsed() {
            return $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                url: checkIfNotUsed,
                method: "GET"
            });
        }

        checkIfRouteNotUsed().done(function (isUsed){
            if (!isUsed){
                $.ajax({

                }).done(function (result){

                }).fail(function (){

                })
            } else {
            //    Write that it is in use
            }
        })

    })
});