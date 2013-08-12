var app = angular.module('thewall', []);

app.filter('moment', function() {
    return function(date, type) {
      if (!date) return

      var date = moment(date)
      if (date.isValid()) {
        switch (type) {
          case 'pretty':
            return date.fromNow()
        }
      }
    }
  }
)

function Wall($scope) {

  $scope.wall = []
  $scope.stats = {}

  var source = new EventSource('/events')

  source.addEventListener('stats', function(e) {
    var message = JSON.parse(event.data)
    $scope.$apply(function() {
      $scope.stats = message
    })
  }, false);

  source.addEventListener('commit', function(e) {
    var message = JSON.parse(event.data)

    $scope.$apply(function() {
      if (message.id) {
        $scope.wall.unshift(message)
      }
    })
  }, false);

}