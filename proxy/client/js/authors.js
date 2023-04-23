function fetchAuthors() {
  var xhttp = new XMLHttpRequest();
  xhttp.onload = function() {
    var authors = JSON.parse(xhttp.responseText);
    initAuthorsList(authors);
  }
  xhttp.open("GET", "http://localhost/api/authors", true);
  xhttp.send();

  /*
  var authors = [];

  var author1 = {
    id: 1,
    firstName: "Ray",
    lastName: "Bradbury"
  };

  var author2 = {
    id: 2,
    firstName: "Leo",
    lastName: "Tolstoy"
  };

  var author3 = {
    id: 3,
    firstName: "Gabriel",
    lastName: "Garcia Marquez"
  };

  authors.push(author1);
  authors.push(author2);
  authors.push(author3);

  return authors;
  */
}

currentAuthorId = 4;

function initAuthorsList(authors) {
  var authorsList = document.getElementById("authors-list");

  for (i = 0; i < authors.length; i++) {
    addAuthorToList(authorsList, authors[i]);
  }
}

function addAuthorToList(authorsList, author) {
  var authorFullName = author.firstName + " " + author.lastName;

  var authorDiv = document.createElement("div");
  authorDiv.innerHTML += authorFullName;
  authorDiv.className = "author-list-item-content"
  
  var removeButton = document.createElement("div");
  removeButton.innerHTML += "x"
  removeButton.addEventListener("click", removeAuthor);
  removeButton.className = "author-list-item-content author-list-item-remove-button";

  var authorsListItem = document.createElement("div");
  authorsListItem.appendChild(authorDiv);
  authorsListItem.appendChild(removeButton);
  authorsListItem.className = "author-list-item";
  authorsListItem.setAttribute("data-author-id", author.id);

  authorsList.appendChild(authorsListItem);
}

function removeAuthorFromList(authorId) {
  var author = document.querySelector(`[data-author-id='${authorId}']`);
  author.parentNode.removeChild(author);
}

function createAuthor() {
  var authorFirstName = document.getElementById("author-first-name").value;
  var authorLastName = document.getElementById("author-last-name").value;

  // Send request to server..

  var author = {
    id: currentAuthorId,
    firstName: authorFirstName,
    lastName: authorLastName
  };

  currentAuthorId += 1;
  var authorsList = document.getElementById("authors-list");
  addAuthorToList(authorsList, author);
}

function removeAuthor(event) {
  var authorToRemoveId = event.currentTarget.parentNode.dataset.authorId;

  // Send request to server..

  removeAuthorFromList(authorToRemoveId);
}
