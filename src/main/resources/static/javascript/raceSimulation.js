let drivers;
let raceStatus;
const intervalId = setInterval(async () => {
    await requestRaceStatus();
    checkRaceStatus();
    if(raceStatus === "STARTED") {
        await requestDriversData();
        updatePositions();
    }
}, 1500);

$(document).ready(function() {
    console.log(intervalId);
});

async function requestDriversData() {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: "GET",
            url: "/getDriverData",
            dataType: "json",
            success: function (driversJSON) {
                drivers = driversJSON;
                console.log(driversJSON);
                resolve();
            },
            error: function (error) {
                console.error("Error occurred:", error);
                reject(error);
            },
        });
    });
}

async function requestRaceStatus() {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: "GET",
            url: "/getRaceStatus",
            dataType: "json",
            success: function (raceStatusJSON) {
                raceStatus = raceStatusJSON;
                console.log(raceStatus);
                resolve();
            },
            error: function (error) {
                console.error("Error occurred:", error);
                reject(error);
            },
        });
    });
}

function checkRaceStatus(){
    if(raceStatus === "FINISHED"){
        clearInterval(intervalId);
    }
}

function updatePositions() {
    for(var i = 0; i < drivers.length; i++){
        updateDriverCard(drivers[i], i);
    }
}
function updateDriverCard(driver, index){
    let driverCard = document.getElementById(`Driver${index}`);
    let driverCardElements = driverCard.getElementsByTagName("div");
    //Team Color
    driverCardElements[0].setAttribute("id", driver.team.teamName);
    //Driver Name
    driverCardElements[2].textContent = driver.fullName;
    //Gap/Status
    if(driver.status === "ReliabilityDNF" || driver.status === "CrashDNF"){
        driverCardElements[3].textContent = "DNF";
    }else{
        if(index !== 0){
            driverCardElements[3].textContent = "+" + formatTime(drivers[index-1].raceTime - driver.raceTime);
        }
    }
}

function formatTime(time) {
    // Convert to seconds
    return (time / 1000).toFixed(3);
}