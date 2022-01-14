# ShareLocApi

Bienvenue sur l'API de shareloc dans le readme nous allons détailler plusieurs points :
* La structure du code
* les routes en déploiement
* l'accès à la base de données

## La structure du code 
Tout est dans le même package -> com.example.sharelockapi
chaque catégorie est stocké dans un package fils :
* controllers : contient toutes les classes qui agissent sur le DAO
* dao : les classes d'accès à la base de données
* filter : contient un filtre pour les requete CORS
* jsonObjects : les classes json pour pouvoir travailler avec des objets json dans mes routes
* model : contient toutes les entités
* paths : contient  toutes les routes de l'api

## les routes en déploiement
la route d'accès à l'api :

http://54.36.191.192:8080/ShareLockApi-1.0-SNAPSHOT/api/

les routes en déploiement :
* Authentification
  * ../api/signup - POST - json : {login,password,firstname,lastname} - créer un compte 
  * ../api/signin - POST - json : {login,password} - se connecter
  * ../api/whoami - GET -  header : {JWT} - route whoami pour connaitre son identité 

* Category
  * ../api/category/index - GET - header : {JWT} - afficher toutes les catégories des taches
  * ../api/category/create - POST - header : {JWT} - json : {label} - créer une tache

* HouseShare
  * ../api/househare/index - GET - header : {JWT} - afficher toutes les collocs
  * ../api/househare/create - POST - header : {JWT} - json : {name,description} - créer une colloc
  * ../api/househare/delete - DELETE - header : {JWT} - queryParam : {id} - delete colloc by id

* Tasks
  * ../api/tasks/index - GET - header : {JWT} - QueryParam : {idHouseShare} -afficher toutes les taches (selon l'utilisateur et la colloc)
  * ../api/tasks/create - POST - header : {JWT} - json {title,description,point,idCategory,idHouseShare} - créer une tache
  * ../api/tasks/delete - POST - (not working for json)

## Remote database

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

* $ payara5/bin/asadmin stop-domain
* $ payara5/bin/asadmin start-domain
or
* $ payara5/bin/asadmin restart-domain










