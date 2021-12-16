# ShareLocApi

Base For Shareloc api : 

Create api in order to create users, tasks, and organization


For database management, we will use database from LP1



# How to test remote database 

on doit faire une redirection de port 

ssh -L NumeroDePort:localhost:3306 ls1@cdad178.iutrs.unistra.fr

starwolf

d'ici là on peut acceder à notre base de donnée 

avec comme url 

jdbc:mysql://localhost:3630/db_ShareLoc?useSSL=false

-> user : ApiShareloc
-> password : yassin
-> host : localhost 
-> database : db_shareloc

# Remote database

ipv4 : 54.36.191.192 
Database Port : 3306
db : shareloc 
id : yassin
mdp : ask for it

to create a war file : 
jar -cvf filename.war * 
run this at root

then copy war file to the autodeploy
cp file.war payara5/glassfish/domains/domain1/autodeploy/
then to start domain 
$ payara5/bin/asadmin stop-domain
$ payara5/bin/asadmin start-domain









