window.onreload = watchReload();

var lastReload;

function watchReload() {
  fetch('/reload?' + new Date().getTime())
    .then(data => {return data.text()})
    .catch(error => console.log(error))
    .then(result => checkReloadResult(result));
}

function checkReloadResult(result) {
  if (lastReload === undefined) {
    lastReload = result;
  }

  if(lastReload != result) {
    document.location.reload();
    return;
  }

  scheduleReloadCheck();
}

function scheduleReloadCheck() {
  setTimeout(function() {
    watchReload();
  }, 500);
}
