let btn = document.querySelector("#btn");
        let sidebar = document.querySelector(".sidebar");
        let searchBtn = document.querySelector(".bx-search");

        btn.onclick = function () {
            sidebar.classList.toggle("active")
        }
        searchBtn.onclick = function () {
            sidebar.classList.toggle("active")
        }

var sideBarLinks = document.getElementsByClassName("siisbaab");

sideBarLinks[0].href = "Grupos.html";
sideBarLinks[1].href = "Jogadores.html";