function fetchAuthors() {
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

    listItem.addEventListener("click", removeAuthor);
    listItem.setAttribute("data-author-id", authors[i].id);
    listItem.appendChild(textNode);

    authorsList.appendChild(listItem);
  }
}

function addNewAuthor() {
  var authorFirstName = document.getElementById("author-first-name").value;
  var authorLastName = document.getElementById("author-last-name").value;

  var author = {
    id: currentAuthorId,
    firstName: authorFirstName,
    lastName: authorLastName
  };

  currentAuthorId += 1;
  authors.push(author);
  
  displayAuthors();
}

function removeAuthor(event) {
  var authorToRemoveId = event.currentTarget.dataset.authorId;
  authors = authors.filter(author => author.id != authorToRemoveId);

  displayAuthors();
}
