var source=new EventSource('/events');
source.onmessage = function(event) {
  console.log('SSE: ', event.data)
}