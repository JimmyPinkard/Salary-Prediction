FROM mariadb:latest

COPY ./init.sql /docker-entrypoint-initdb.d/init.sql
COPY load_db.sh /docker-entrypoint-initdb.d/load_db.sh