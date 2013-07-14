require 'railsless-deploy'

# Your application name
set :application, "thewall"

# We're not deploying from a repo, since this is scala and we
# need to compile. Set SCM to none
set :scm, :none

# Our deploy is to copy the contents of…
set :deploy_via, :copy

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

after 'deploy', 'deploy:restart'
after 'deploy:restart', 'deploy:cleanup'

namespace :deploy do
  task :restart do
    stop
    start
  end

  # Override start run current/start. The options are options to play
  # specifying a config file and pidfile
  task :start do
    run "#{current_path}/start -Dhttp.port=80 -Dconfig.file=#{current_path}/application.conf -Dpidfile.path=#{shared_path}/thewall.pid >/dev/null 2>&1 &"
  end
  # Handle killing a running instance
  task :stop do
    run "kill -15 `cat #{shared_path}/thewall.pid`"
  end
end