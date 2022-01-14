


#Génération du fichier .WAR
jar -cvf ShareLoc-API.war *

#copie du fichier sur le serveur distant
scp ShareLoc-API.war debian@54.36.191.192:/home/debian
ssh debian@54.36.191.192






