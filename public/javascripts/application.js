function Wall($scope) {

  $scope.wall = []

  var source = new EventSource('/events')
  source.onmessage = function(event) {
    var message = JSON.parse(event.data)
    console.log('SSE: ', message)

    $scope.$apply(function() {
      if (message.id) {
        $scope.wall.push(message)
      }
    })
  }

//  var source = new WebSocket('ws://'+document.location.host+'/websockets')
//  source.onmessage = function(event) {
//    var message = JSON.parse(event.data)
//    console.log('SSE: ', message)
//
//    $scope.$apply(function() {
//      if (message.id) {
//        $scope.wall.push(message)
//      }
//    })
//  }
}