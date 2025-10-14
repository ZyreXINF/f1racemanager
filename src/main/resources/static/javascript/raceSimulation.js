$(document).ready(function() {
    requestDriverPositionsData();
});

function requestDriverPositionsData(){
    $.ajax({
        type: "GET",
        url: "/getDriverPositions",
        dataType: 'json',
        success: function (response){
            console.log(response);
        },
        error: function (error) {
          console.error("Error occured:", error);
        }
      });
}