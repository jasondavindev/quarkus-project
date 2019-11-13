export PROJECT_NAME="final_project_lp2"
export PROJ_BASE=$(pwd)

function dkbuild {
  CD=$(pwd)
  cd $PROJ_BASE
  ./mvnw package
  exitcode=$?
  if [ "$exitcode" -eq "0" ]
  then
    docker build -t "$PROJECT_NAME" -f docker/Dockerfile.jvm .
    exitcode=$?
  fi
  cd $CD
  return $exitcode
}

function dkupa {
  CD=$(pwd)
  cd $PROJ_BASE
  docker-compose -f docker/docker-compose.yml up
  exitcode=$?
  cd $CD
  return $exitcode
}

function dkupd {
  CD=$(pwd)
  cd $PROJ_BASE
  docker-compose -f docker/docker-compose.yml up -d
  exitcode=$?
  cd $CD
  return $exitcode
}

function dkdown {
  CD=$(pwd)
  cd $PROJ_BASE
  docker-compose -f docker/docker-compose.yml down
  exitcode=$?
  cd $CD
  return $exitcode
}

function dkexec {
  docker exec -ti "$PROJECT_NAME"_app $@
}