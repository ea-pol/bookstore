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
    displayBooks();
    updateAuthorSelector();
  });

  addBookButton = document.getElementById("add-book");
  addBookButton.addEventListener("click", addNewBook);
  
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
    displayStats();
  });
}

currentAuthorId = 4;
authors = fetchAuthors();

currentBookId = 5;
books = fetchBooks();

var stats = fetchStats();

initEventListeners();
document.getElementById("tabauthors").click();
