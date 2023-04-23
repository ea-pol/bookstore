function openTab(evt, tabName) {
  var i, tabcontent, tablinks;

  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  tabinputs = document.getElementsByClassName("tabcontent-input-form");
  for (i = 0; i < tabinputs.length; i++) {
    tabinputs[i].style.display = "none";
  }

  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  document.getElementById(tabName).style.display = "block";
  document.getElementById(tabName + "-input").style.display = "block";
  evt.currentTarget.className += " active";
}

function initEventListeners() {
  /* Books */
  tabbooks = document.getElementById("tabbooks");
  tabbooks.addEventListener("click", (event) => {
    openTab(event, "books");
  });

  addBookButton = document.getElementById("add-book");
  addBookButton.addEventListener("click", createBook);
  
  /* Authors */
  tabauthors = document.getElementById("tabauthors");
  tabauthors.addEventListener("click", (event) => {
    openTab(event, "authors");
  });

  addAuthorButton = document.getElementById("add-author");
  addAuthorButton.addEventListener("click", createAuthor);

  /* Stats */
  tabstats = document.getElementById("tabstats");
  tabstats.addEventListener("click", (event) => {
    openTab(event, "stats");
    displayStats();
  });
}

function checkServerStatus() {
  var xhttp = new XMLHttpRequest();
  xhttp.onload = function() {
    console.log("Server is up!");
  }
  xhttp.open("GET", "http://localhost/api/status", true);
  xhttp.send();
}

var stats = fetchStats();

initEventListeners();
fetchAuthorsList();
fetchBooksList();

document.getElementById("tabauthors").click();
checkServerStatus();
