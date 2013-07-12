var ws = new WebSocket('ws://'+document.location.host+'/websocket')
ws.onmessage = function(evt) {
  console.log(evt.data)
}

//var source=new EventSource('/events');
//source.onmessage = function(event) {
//  console.log(event)
//}