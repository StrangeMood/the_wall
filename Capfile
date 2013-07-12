require 'railsless-deploy'

# Your application name
set :application, "thewall"

# We're not deploying from a repo, since this is scala and we
# need to compile. Set SCM to none
set :scm, :none

# Our deploy is to copy the contents of…
set :deploy_via, :copy
set :copy_compression, false

# the target directory! In this case repository is really
# a pointer to the directory
set :repository,  'target'

# You can use multiple here, if that's what you have
role :web, '192.241.131.22'

set :deploy_to, '/opt/thewall'
set :user, 'root'

set :use_sudo, false

# Skip bits in "target" that we don't want.
set :copy_exclude, ['streams', 'scala*']

after 'deploy:restart', 'deploy:cleanup'

namespace :deploy do
  # Override start run current/start. The options are options to play
  # specifying a config file and pidfile
  task :start do
    run 'nohup /opt/thewall/current/start -Dconfig.file=/opt/thewall/current/production.conf -Dhttp.port=80 -Dpidfile.path=/opt/thewall/thewall.pid >/dev/null 2>&1 &'
  end
  # Handle killing a running instance
  task :stop do
    run "kill -15 `cat /opt/thewall/thewall.pid`"
  end
end