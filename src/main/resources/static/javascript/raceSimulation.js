let drivers, oldDriversPositions;
let fastestDriver;

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
            await requestFastestDriver();
            updatePositions();
            updateLapCounter();
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
                console.log(drivers);
                clearDNFEffect();
                clearFastestLapEffect();
                updatePositions();
                updateLapCounter();
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
        await requestFastestDriver();
        updateLapCounter();
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
function updateLapCounter(){
    let lapElement = document.getElementById(`lapCounter`);
    if(raceStatus === "RACING"){
        let lapNumber = drivers[0].currentLap;
        lapElement.textContent = "Lap: " + lapNumber;
    }else if(raceStatus === "FINISHED"){
        lapElement.textContent = "Race Finished";
    }

}
function updateDriverCard(driver, index, positionDifference){
    let driverCard = document.getElementById(`Driver${index}`);
    let driverCardElements = driverCard.getElementsByTagName("div");
    //Team Color
    driverCardElements[0].setAttribute("id", driver.team.teamName);
    //Driver Name
    driverCardElements[2].textContent = driver.fullName;
    if(driver.fullName === fastestDriver.fullName){
        clearFastestLapEffect();
        driverCardElements[0].classList.add("fastest");
    }
    //Gap/Status
    if(driver.status === "ReliabilityDNF" || driver.status === "CrashDNF"){
        driverCardElements[3].textContent = "DNF";
        driverCardElements[3].textContent = "DNF";
        driverCardElements[0].classList.add("dnf");
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
    return (time / 1000).toFixed(3);
}
function flashCard(card, direction) {
    const flashClass = direction === "up" ? "flash-up" : "flash-down";
    card.classList.remove("flash-up", "flash-down");
    void card.offsetWidth;
    card.classList.add(flashClass);
}
function clearDNFEffect(){
    for(var i = 0; i < drivers.length; i++){
        let driverCard = document.getElementById(`Driver${i}`);
        let driverCardElements = driverCard.getElementsByTagName("div");
        driverCardElements[0].classList.remove("dnf");
    }
}
function clearFastestLapEffect(){
    for(var i = 0; i < drivers.length; i++){
        let driverCard = document.getElementById(`Driver${i}`);
        let driverCardElements = driverCard.getElementsByTagName("div");
        driverCardElements[0].classList.remove("fastest");
    }
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
async function requestFastestDriver(){
    return new Promise((resolve, reject) => {
        $.ajax({
            type: "GET",
            url: "/getFastestDriver",
            dataType: "json",
            success: function (fastestDriverJSON) {
                fastestDriver = fastestDriverJSON;
                console.log(fastestDriver);
                resolve();
            },
            error: function (error) {
                console.error("Error occurred:", error);
                reject(error);
            },
        });
    });
}