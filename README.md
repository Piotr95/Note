# Note Back-end service and History



## Steps to Setup
# I.Install PostgreSQL  


# II. Create the Database 

First of all create the user ``note`` for connecting to the database:

```
PS C:\Users\piotr> psql -U postgres
psql (10.5)
postgres=# CREATE USER note WITH PASSWORD 'notepass';
CREATE ROLE
```

Then we can create the database ``notes`` and set the owner to ``note``:

```
postgres=# CREATE DATABASE notes  WITH OWNER note;
CREATE DATABASE
```
 # III.Open NoteBack-end and run it by terminal/cmd      
```
mvn spring-boot:run
```
 For  API  usage  examples  go to  http://localhost:8080/swagger-ui.html
 
# IV Open NoteHistory and run it like in III

For  API  usage  examples  go to  http://localhost:3333/swagger-ui.html

# V Test data are initialized  (in NoteBack-end)

# Enjoy :)
