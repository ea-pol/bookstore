function fetchAuthorsList() {
  var xhttp = new XMLHttpRequest();

  xhttp.onload = function() {
    var authors = JSON.parse(xhttp.responseText);
    var authorsList = document.getElementById("authors-list");

    for (i = 0; i < authors.length; i++) {
      addAuthorToList(authorsList, authors[i]);
    }

    updateAuthorSelector();
  }

  xhttp.open("GET", "http://localhost/api/authors", true);
  xhttp.send();
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
  authorsListItem.setAttribute("data-author-id", author.authorId);

  authorsList.appendChild(authorsListItem);
}

function removeAuthorFromList(authorId) {
  var author = document.querySelector(`[data-author-id='${authorId}']`);
  author.parentNode.removeChild(author);
}

function createAuthor() {
  var authorFirstName = document.getElementById("author-first-name").value;
  var authorLastName = document.getElementById("author-last-name").value;

  var newAuthor = {
    firstName: authorFirstName,
    lastName: authorLastName
  };

  var xhttp = new XMLHttpRequest();

  xhttp.onload = function() {
    var author = JSON.parse(xhttp.responseText);
    var authorsList = document.getElementById("authors-list");
    addAuthorToList(authorsList, author);
    updateAuthorSelector();
  }
  
  xhttp.open("POST", "http://localhost/api/authors", true);
  xhttp.setRequestHeader('Content-type', 'application/json');
  xhttp.send(JSON.stringify(newAuthor));
}

function removeAuthor(event) {
  var authorToRemoveId = event.currentTarget.parentNode.dataset.authorId;

  var xhttp = new XMLHttpRequest();

  xhttp.onload = function() {
    removeAuthorFromList(authorToRemoveId);
    fetchBooksList();
    updateAuthorSelector();
  }

  xhttp.open("DELETE", "http://localhost/api/authors/" + authorToRemoveId, true);
  xhttp.send();
}

function updateAuthorSelector() {
  var authorSelector = document.getElementById("author-selector");
  var authorsList = document.getElementById("authors-list").children;

  authorSelector.innerHTML = "";

  for (i = 0; i < authorsList.length; i++) {
    var authorOption = document.createElement("option");
    authorOption.innerHTML += authorsList[i].children[0].innerHTML;
    authorOption.setAttribute("value", authorsList[i].dataset.authorId);
    authorSelector.appendChild(authorOption);
  }
}
