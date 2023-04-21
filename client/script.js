function fetchAuthors() {
  var authors = [];

  var author1 = {
    firstName: "Ray",
    lastName: "Bradbury"
  };

  var author2 = {
    firstName: "Leo",
    lastName: "Tolstoy"
  };

  var author3 = {
    firstName: "Thomas",
    lastName: "Anderson"
  }

  authors.push(author1);
  authors.push(author2);
  authors.push(author3);

  return authors;
}

function displayAuthors() {
  var authorsList = document.getElementById("authors-list");
  authorsList.innerHTML = "";

  for (i = 0; i < authors.length; i++) {
    var authorFullName = authors[i].firstName + " " + authors[i].lastName;
    var textNode = document.createTextNode(authorFullName);
    var listItem = document.createElement("li");
    listItem.appendChild(textNode);
    authorsList.appendChild(listItem);
  }
}

function addNewAuthor() {
  var authorFirstName = document.getElementById("author-first-name").value;
  var authorLastName = document.getElementById("author-last-name").value;

  var author = {
    firstName: authorFirstName,
    lastName: authorLastName
  };

  authors.push(author);
  displayAuthors();
}

function openTab(evt, tabName) {
  var i, tabcontent, tablinks;

  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
}

function initEventListeners() {
  /* Books */
  tabbooks = document.getElementById("tabbooks");
  tabbooks.addEventListener("click", (event) => {
    openTab(event, "books");
  });
  
  /* Authors */
  tabauthors = document.getElementById("tabauthors");
  tabauthors.addEventListener("click", (event) => {
    openTab(event, "authors");
    displayAuthors();
  });

  addAuthorButton = document.getElementById("add-author");
  addAuthorButton.addEventListener("click", addNewAuthor);
  
  /* Stats */
  tabstats = document.getElementById("tabstats");
  tabstats.addEventListener("click", (event) => {
    openTab(event, "stats");
  });
}

authors = fetchAuthors();
initEventListeners();
document.getElementById("tabbooks").click();