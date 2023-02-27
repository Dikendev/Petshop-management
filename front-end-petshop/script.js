const apiUrl = "http://localhost:8080/api";

function getData(entity) {
    fetch(`${apiUrl}/${entity}`);
}

function load() {
    fetch(`${apiUrl}/animals`).then((response) => {
        return response.json();
    })
}

function sendEntity() {
    let data;
    let entity = this.getAttribute("entity");
    if (entity === "animals") {
       // const selectTreatType = document.querySelector("#animalTreatType");
        data = {
            name: document.querySelector("#animalName").value,
            species: document.querySelector("#animalSpecie").value,
            breed: document.querySelector("#animalBreed").value,
            weigth: document.querySelector("#animalWeight").value,
            hair_type: document.querySelector("#animalHairType").value,
           // treat_type: selectTreatType.options[selectTreatType.selectedIndex].value,
        };
        console.log(data);
    } fetch(`${apiUrl}/${entity}`, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    }).then((response) => {
        return response.json();
    }).then((body) => {
        console.log(body);
    }).catch(error => {
        alert("We had some troubles, console message: " + error.message);
        console.log(error);
    })
}

function deleteEntity() {
    document.querySelector("#deleteList").innerHTML = "";
    entity = this.getAttribute("entityRef");
    document.querySelector("#deleteEntityButton").setAttribute("entity", entity);
    fetch(`${apiUrl}/${entity}`).then((response) => {
        return response.json();
    }).then((body) => {
        body.forEach(entity => {
            document.querySelector("#deleteList").innerHTML += `<option value="${entity.id}">${(entity.name)}</option>`
        })
    })
}

function sendDelete(){
    console.log(`${apiUrl}/animals/${document.querySelector("#deleteList").value}`);
    fetch(`${apiUrl}/animals/${document.querySelector("#deleteList").value}`, {
        method: "DELETE"
    }).then(response => {
        return response.json();
    }).then(body => {
        alert(body);
    }).catch(error => {
        alert(error);
    });
}

function queryButtonEvent() {
    document.querySelector("#tableHeader").innerHTML = "";
    document.querySelector("#tableBody").innerHTML = "";
    fetch(`${apiUrl}/animals`).then((response) => {
        return response.json();
    }).then((body) => {
        if (body.length <= 0) {
            alert("No data found!");
        } else {
            createTableHeader(body);
            body.forEach((entity, index) => {
                bodyStructure = "<tr>";
                Object.getOwnPropertyNames(entity).forEach((e) => {
                    console.log(typeof (body[index][e]));
                    bodyStructure += `<td>${(typeof (body[index][e]) == "object") ? body[index][e].name : body[index][e]}</td>`
                });
                bodyStructure += `<td><a href="#" onclick="updateEntity('${this.getAttribute("entityRef")}', '${body[index]["id"]}')">Update</a></td></tr>`;
                document.querySelector("#tableBody").innerHTML += bodyStructure;
            });
        }
    });
}

function createTableHeader(entity) {
    headerStructure = "<tr>";
    Object.getOwnPropertyNames(entity[0]).forEach((e) => {
        headerStructure += `<th>${e}</th>`;
    });
    headerStructure += "<th>Update</th>";
    document.querySelector("#tableHeader").innerHTML = headerStructure;
}

document.querySelectorAll(".selectButton").forEach((button) => button.addEventListener("click", queryButtonEvent));
document.querySelectorAll(".sendEntityButton").forEach((button) => button.addEventListener("click", sendEntity));
document.querySelectorAll(".deleteDropdownButton").forEach((button) => button.addEventListener("click", deleteEntity));
document.querySelector("#deleteEntityButton").addEventListener("click", sendDelete)
load();