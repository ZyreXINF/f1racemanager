let drivers, oldDriversPositions;

let raceStatus;
let currentLap;

let intervalId;

$(document).ready(async function () {
    // await requestRaceStatus();
    await requestSessionData();


    switch (raceStatus) {
        case "RACING":
            intervalId = setInterval(raceLogic, 1500);
            disableButton();
            break;
        case "FINISHED":
            // await requestDriversData();
            await requestSessionData();
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
                // await requestDriversData();
                await requestSessionData();
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
    // await requestRaceStatus();
    await requestSessionData();
    checkRaceStatus();
    if(raceStatus === "RACING") {
        // await requestDriversData();
        updatePositions();
    }
    updateLapCounter();
}
function checkRaceStatus(){
    if(raceStatus === "FINISHED"){
        clearInterval(intervalId);
        changeButton();
    }
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
        lapElement.textContent = "Lap: " + lapNumber + "/" + lapAmount;
    }else if(raceStatus === "FINISHED"){
        lapElement.textContent = "Race Finished";
    }

}
function updateDriverCard(driver, index, positionDifference) {
    const driverCard = document.getElementById(`Driver${index}`);
    const elements = driverCard.getElementsByTagName("div");

    updateTeamStripe(elements[0], driver);
    updateDriverName(elements[2], driver);
    updateFastestLapState(elements[0], driver);
    updateDriverGapOrStatus(elements, driver, index);
    updateCardMovement(elements[0], positionDifference);
}
function updateTeamStripe(teamStripeElement, driver) {
    teamStripeElement.setAttribute("id", driver.team.teamName);
}
function updateDriverName(nameElement, driver) {
    nameElement.textContent = driver.fullName;
}
function updateFastestLapState(teamStripeElement, driver) {
    if (driver.setFastestLap) {
        clearFastestLapEffect();
        teamStripeElement.classList.add("fastest");
    }
}
function updateDriverGapOrStatus(elements, driver, index) {
    const gapElement = elements[3];
    const teamStripe = elements[0];

    if (driver.status === "ReliabilityDNF" || driver.status === "CrashDNF") {
        gapElement.textContent = "DNF";
        teamStripe.classList.add("dnf");
    } else if (index !== 0) {
        gapElement.textContent = "+" + formatTime(driver.raceTime - drivers[index - 1].raceTime);
    }
}
function updateCardMovement(teamStripeElement, positionDifference) {
    if (positionDifference > 0) {
        flashCard(teamStripeElement, "up");
    } else if (positionDifference < 0) {
        flashCard(teamStripeElement, "down");
    }
}
//Formating race time for display
function formatTime(time) {
    return (time / 1000).toFixed(3);
}
//flashing card on position update
function flashCard(card, direction) {
    const flashClass = direction === "up" ? "flash-up" : "flash-down";
    card.classList.remove("flash-up", "flash-down");
    void card.offsetWidth;
    card.classList.add(flashClass);
}
//Clearing Special Visuals
function clearDNFEffect(){
    for(let i = 0; i < drivers.length; i++){
        let driverCard = document.getElementById(`Driver${i}`);
        let driverCardElements = driverCard.getElementsByTagName("div");
        driverCardElements[0].classList.remove("dnf");
    }
}
function clearFastestLapEffect(){
    for(let i = 0; i < drivers.length; i++){
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

//API Communication
async function requestSessionData() {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: "GET",
            url: "/getSessionData",
            dataType: "json",
            success: function (sessionDataJSON) {
                if (!drivers) {
                    oldDriversPositions = sessionDataJSON.driversList.map(d => d.fullName);
                } else {
                    oldDriversPositions = drivers.map(d => d.fullName);
                }
                drivers = sessionDataJSON.driversList;
                console.log(oldDriversPositions);
                console.log(drivers);
                raceStatus = sessionDataJSON.raceStatus;
                currentLap = sessionDataJSON.currentLap;
                lapAmount = sessionDataJSON.lapAmount;
                resolve();
            },
            error: function (error) {
                console.error("Error occurred:", error);
                reject(error);
            },
        });
    });
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