function httpGet(theUrl) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

function cadastrateMesate(Nome, NomeDM){
    console.log(Nome);
    console.log(NomeDM);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/mesa/cadastro", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
    "nome": Nome,
    "dm": NomeDM
}));}

function eraseFromReality(ide){
    console.log(ide);
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8080/mesa?id=" + ide, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(null);
    window.location.reload();
}


function editateMesate(Nome, ide){
    console.log(Nome);
    var xhr = new XMLHttpRequest();
    xhr.open("PUT", "http://localhost:8080/mesa?id=" + ide, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
    "nome": Nome
}));
}

function makeButtonGo(){
    var inputArray = document.getElementsByClassName('inputText');
    var postbutton = document.getElementsByClassName('btnAdicionarMembroGrupo');
    cadastrateMesate(inputArray[0].value);
    window.location.reload();
}

function cancel(){
    window.location.reload();
}

function send(ide){
    var nome = document.getElementsByClassName("inputText")[0].value;
    console.log(nome)
    editateMesate(nome,ide);
    window.location.reload();
}

function openScreen(idval){
    var thistr = document.getElementById(idval);
    var actnbtn = thistr.getElementsByTagName("td");
    var playerid = actnbtn[0].innerHTML;
    actnbtn[4].remove();

    var field = document.createElement("td");
    var send = document.createElement("td");
    var falous = document.createElement("td");
    var cancel = document.createElement("td");

    field.setAttribute('data-label', 'Ação');
    send.setAttribute('data-label', 'Ação');
    cancel.setAttribute('data-label', 'Ação');
    falous.setAttribute('data-label', 'Ação');

    field.innerHTML = '<input type="text" class="inputText"></input>';
    send.innerHTML = `<a onclick="send('${playerid}')" href="#" class="btnJoin">Confirmar</a>`;
    cancel.innerHTML = `<a onclick="cancel()" href="#" class="btnJoin">Cancelar</a>`;
    falous.innerHTML = `<a onclick="eraseFromReality('${playerid}')" href="#" class="btnJoin">Deletar</a>`;

    thistr.appendChild(field);
    thistr.appendChild(send);
    thistr.appendChild(falous);
    thistr.appendChild(cancel);
}

const allTables = httpGet("http://localhost:8080/mesa/testall"); //INSERIR URL PRA PEGAR AQUI

var splitTable = allTables.split(",");

splitTable = JSON.parse(splitTable);

for (var i = 0; i < splitTable.length; i++) {

    //console.log(splitTable[i]);

    var tr = document.createElement("tr");
    var ID = document.createElement("td");
    var Grupo = document.createElement("td");
    var Mestre = document.createElement("td");
    var Jogadores = document.createElement("td");
    var ButtonEnter = document.createElement("td");
    var ButtonEdit = document.createElement("td");

    tr.setAttribute('id', i);
    ID.setAttribute('data-label', 'ID');
    Grupo.setAttribute('data-label', 'Grupo');
    Mestre.setAttribute('data-label', 'Mestre');
    Jogadores.setAttribute('data-label', 'Jogadores');
    ButtonEnter.setAttribute('data-label', 'Ação');
    ButtonEdit.setAttribute('data-label', 'Ação');

    ID.innerHTML = splitTable[i].id;
    Grupo.innerHTML = splitTable[i].nome;
    Mestre.innerHTML = splitTable[i].dm;
    Jogadores.innerHTML = splitTable[i].jogadores==null ? 0 : splitTable[i].jogadores.length;
    ButtonEnter.innerHTML = '<a href="#" class="btnJoin">Entrar</a>';
    ButtonEdit.innerHTML = `<a onclick="openScreen('${i}')" href="#" class="btnJoin">Editar</a>`;


    tr.appendChild(ID);
    tr.appendChild(Grupo);
    tr.appendChild(Mestre);
    tr.appendChild(Jogadores);
    tr.appendChild(ButtonEnter);
    tr.appendChild(ButtonEdit);

    var tablebody = document.getElementById("tableBody");
    tablebody.appendChild(tr);

}

