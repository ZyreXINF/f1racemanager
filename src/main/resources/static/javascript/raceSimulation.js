let drivers;
let raceStatus;
let intervalId;

$(document).ready(async function () {
    await requestRaceStatus();

    switch (raceStatus) {
        case "STARTED":
            intervalId = setInterval(raceLogic, 1500);
            disableButton();
            break;
        case "FINISHED":
            await requestDriversData();
            updatePositions();
            changeButton();
            break;
    }

    $('#race-button').click(async function () {
        switch (raceStatus) {
            case "READY":
                await startRace();
                disableButton();
                intervalId = setInterval(raceLogic, 1500);
                break;
            case "FINISHED":
                await restartRace();
                await requestDriversData();
                updatePositions();
                disableButton();
                intervalId = setInterval(raceLogic, 1500);
                break;
        }
    });
});

async function raceLogic(){
    await requestRaceStatus();
    checkRaceStatus();
    if(raceStatus === "STARTED") {
        await requestDriversData();
        updatePositions();
    }
}

async function startRace() {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: "POST",
            url: "/startRace",
            success: function () {
                resolve();
            },
            error: function (error) {
                console.error("Error occurred:", error);
                reject(error);
            },
        });
    });
}

async function restartRace() {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: "POST",
            url: "/restartRace",
            success: function () {
                resolve();
            },
            error: function (error) {
                console.error("Error occurred:", error);
                reject(error);
            },
        });
    });
}

async function requestDriversData() {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: "GET",
            url: "/getDriverData",
            dataType: "json",
            success: function (driversJSON) {
                drivers = driversJSON;
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
        changeButton();
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
            driverCardElements[3].textContent = "+" + formatTime(driver.raceTime - drivers[index-1].raceTime);
        }
    }
}

function formatTime(time) {
    // Convert to seconds
    return (time / 1000).toFixed(3);
}

function disableButton(){
    let button = document.getElementById("race-button");
    button.disabled = true;
    button.textContent = "Race Started";
}

function changeButton(){
    let button = document.getElementById("race-button");
    button.disabled = false;
    button.textContent = "Restart";
}