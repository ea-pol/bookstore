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

tabbooks = document.getElementById('tabbooks');
tabbooks.addEventListener('click', (event) => {
  openTab(event, 'books');
});

tabauthors = document.getElementById('tabauthors');
tabauthors.addEventListener('click', (event) => {
  openTab(event, 'authors');
});

tabstats = document.getElementById('tabstats');
tabstats.addEventListener('click', (event) => {
  openTab(event, 'stats');
});

document.getElementById('tabbooks').click();
