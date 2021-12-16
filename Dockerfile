#conteneur payara
FROM payara/micro
COPY Sharelock.war $DEPLOY_DIR
