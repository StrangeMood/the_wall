function Wall($scope) {

  $scope.wall = []

  var source = new EventSource('/events')

  source.addEventListener('commit', function(e) {
    var message = JSON.parse(event.data)

    $scope.$apply(function() {
      if (message.id) {
        $scope.wall.unshift(message)
      }
    })
  }, false);

}