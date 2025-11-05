let drivers, oldDriversPositions;

let raceStatus;
let intervalId;

$(document).ready(async function () {
    await requestRaceStatus();

    switch (raceStatus) {
        case "RACING":
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
    if(raceStatus === "RACING") {
        await requestDriversData();
        updatePositions();
    }
}
function checkRaceStatus(){
    if(raceStatus === "FINISHED"){
        clearInterval(intervalId);
        changeButton();
    }
}

//Race control
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

//UI Updates
function updatePositions() {
    for(var i = 0; i < drivers.length; i++){
        const driver = drivers[i];
        const newPosition = i;
        const oldPosition = oldDriversPositions.indexOf(driver.fullName);
        let positionDifference = oldPosition - newPosition;
        updateDriverCard(driver, i, positionDifference);
    }
}
function updateDriverCard(driver, index, positionDifference){
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
    if(positionDifference > 0){
        flashCard(driverCardElements[0], "up");
    }else if(positionDifference < 0){
        flashCard(driverCardElements[0], "down");
    }
}
function formatTime(time) {
    // Convert to seconds
    return (time / 1000).toFixed(3);
}
function flashCard(card, direction) {
    const flashClass = direction === "up" ? "flash-up" : "flash-down";
    card.classList.remove("flash-up", "flash-down"); // reset any ongoing flash
    void card.offsetWidth; // force reflow to restart animation
    card.classList.add(flashClass);
}

//Button updates
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

//API Requests
async function requestDriversData() {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: "GET",
            url: "/getDriverData",
            dataType: "json",
            success: function (driversJSON) {
                if (!drivers) {
                    oldDriversPositions = driversJSON.map(d => d.fullName);
                } else {
                    oldDriversPositions = drivers.map(d => d.fullName);
                }
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