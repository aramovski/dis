docker run --rm --name postgres -e POSTGRES_PASSWORD=password -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data  postgres
