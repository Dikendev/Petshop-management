const url = "http://localhost:8080";

function getData(entity) {
    fetch(`${url}/${entity}`);
}

function loadDropdownData() {
    fetch(`${url}/pet`).then((response) => {
        return response.json();
    }).then((body) => {
        body.forEach((p => {
            document.querySelector("#registerPet").innerHTML += `<option value="${p.id}">${p.name}</option>`;
        }));
    });

    fetch(`${url}/owner`).then((response) => {
        return response.json();
    }).then((body) => {
        body.forEach((o => {
            document.querySelector("#petOwner").innerHTML += `<option value="${o.id}">${o.name}</option>`;
        }));
    });
}

function sendEntity() {
    let data;
    let entity = this.getAttribute("entity");
    if (entity === "pet") {
        const selectCoat = document.querySelector("#petCoatType");
        const selectOwner = document.querySelector("#petOwner");
        data = {
            name: document.querySelector("#petName").value,
            race: document.querySelector("#petRace").value,
            heigth: document.querySelector("#petHeigth").value,
            weight: document.querySelector("#petWeight").value,
            coatType: selectCoat.options[selectCoat.selectedIndex].value,
            owner: {
                id: selectOwner.options[selectOwner.selectedIndex].value
            }
        };

    } else if (entity === "owner") {
        data = {
            name: document.querySelector("#ownerName").value,
            cpf: document.querySelector("#ownerCpf").value,
            email: document.querySelector("#ownerEmail").value,
            phone: document.querySelector("#ownerPhone").value,
            adress: document.querySelector("#ownerAdress").value
        };
    } else {
        const petId = document.querySelector("#registerPet");
        let registerDate = document.querySelector("#registerDate").value;
        let registerHour = document.querySelector("#registerHour").value;
        formatDate(registerDate, registerHour);
        data = {
            registerDate: formatDate(registerDate, registerHour),
            treatmentType: document.querySelector("#registerTreatment").value,
            pet: {
                id: petId.options[petId.selectedIndex].value
            }

        };
    }
    fetch(`${url}/${entity}`, {
        method: this.getAttribute("mode"),
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
    fetch(`${url}/${entity}`).then((response) => {
        return response.json();
    }).then((body) => {
        body.forEach(entity => {
            document.querySelector("#deleteList").innerHTML += `<option value="${entity.id}">${(entity.name == undefined) ? entity.registerDate : entity.name}</option>`
        })
    })
}

function updateEntity(entity, id) {
    fetch(`${url}/${entity}/${id}`).then((response) => {
        return response.json();
    }).then((body) => {
        if (entity === "pet") {
            document.querySelector("#petName").value = body.name;
            document.querySelector("#petRace").value = body.race;
            document.querySelector("#petHeigth").value = body.heigth;
            document.querySelector("#petWeight").value = body.weight;
            document.querySelector("#sendPet").setAttribute("mode", "update");
            document.querySelector("#petModal div div div h1").innerHTML = "Update pet";
            document.querySelector("#addPet").click();
        } else if (entity === "owner") {
            document.querySelector("#ownerName").value = body.name;
            document.querySelector("#ownerCpf").value = body.cpf;
            document.querySelector("#ownerEmail").value = body.email;
            document.querySelector("#ownerPhone").value = body.phone;
            document.querySelector("#ownerAdress").value = body.adress;
            document.querySelector("#sendOwner").setAttribute("mode", "update")
            document.querySelector("#ownerModal div div div h1").innerHTML = "Update Owner";
            document.querySelector("#addOwner").click();
        } else {
            document.querySelector("#sendRegister").setAttribute("mode", "update")
            document.querySelector("#registerModal div div div h1").innerHTML = "Update Register";
            document.querySelector("#addRegister").click();
        }
    });
}


function queryButtonEvent() {
    document.querySelector("#tableHeader").innerHTML = "";
    document.querySelector("#tableBody").innerHTML = "";
    fetch(`${url}/${this.getAttribute("entityRef")}`).then((response) => {
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

function changeMode() {
    document.querySelectorAll(".sendEntityButton").forEach(b => {
        b.setAttribute("mode", "post");
    });
}

function formatDate(date, hour) {
    let fDate = date.split("/");
    let fHour = hour.split(":");
    return `${fDate[2]}-${fDate[1]}-${fDate[0]} ${fHour[0]}-${fHour[1]}`
}

function sendDelete(){
    console.log(`${url}/${this.getAttribute("entity")}/${document.querySelector("#deleteList").value}`);
    fetch(`${url}/${this.getAttribute("entity")}/${document.querySelector("#deleteList").value}`, {
        method: "DELETE"
    }).then(response => {
        return response.json();
    }).then(body => {
        alert(body);
    }).catch(error => {
        alert(error);
    });
}

document.querySelectorAll(".selectButton").forEach((button) => button.addEventListener("click", queryButtonEvent));
document.querySelectorAll(".deleteDropdownButton").forEach((button) => button.addEventListener("click", deleteEntity));
document.querySelectorAll(".sendEntityButton").forEach((button) => button.addEventListener("click", sendEntity));
document.querySelectorAll(".addDropdownButton").forEach((button) => button.addEventListener("click", changeMode));
document.querySelector("#deleteEntityButton").addEventListener("click", sendDelete)
loadDropdownData();