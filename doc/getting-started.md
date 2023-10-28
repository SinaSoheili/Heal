#Initial Database for development
- Pull `mariadb` docker image
  ```
  docker pull mariadb
  ```
- User following command to create a container from pulled image
    ```
    sudo docker run --detach --name mariadb --env MARIADB_ROOT_PASSWORD=root  -p 3306:3306 mariadb:latest
    ```
- User `root` as username and password and connect to database through `dbeaver` application and create a database with `heal` name
