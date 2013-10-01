var req = null;

function newXMLHttpRequest() {
  var xmlreq = false;
  if (window.XMLHttpRequest) {
    xmlreq = new XMLHttpRequest();
  } else if (window.ActiveXObject) {
    try {
      xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e1) {
      try {
        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
      }
    }
  }
  return xmlreq;
}

function sendQuery(url, fct) {
    req = newXMLHttpRequest();
    if(req!=null) {
        req.onreadystatechange = fct;
        req.open("POST", url, true);
        req.send(null);
    }
}

function listerCategories() {
    sendQuery("lister-categories", afficherCategories);
}

function afficherCategories() {
    if (req!=null && req.readyState == 4) {
        if (req.status == 200) {
            try {
                var respTxt = req.responseText;
                var listCat = document.getElementById("listCat");
                if(listCat != null) {
                    listCat.innerHTML = respTxt;
                }
            } catch(e) {
            }
        }
    }
}