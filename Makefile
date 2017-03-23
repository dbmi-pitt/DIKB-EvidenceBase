compile:
	mvn clean
	mvn compile -Pwebapi-postgresql-dikb

package: compile
	mvn package -Pwebapi-postgresql-dikb

deploy: package
	#sudo service tomcat7 shutdown -- restore once the default ubuntu tomcat7 is re-installed (with proper port conf)
	sudo /var/lib/tomcat7-DIKB/bin/shutdown.sh 
	sleep 4
	sudo rm -rf /var/lib/tomcat7-DIKB/webapps/WebAPI*
	sudo cp -r target/WebAPI /var/lib/tomcat7-DIKB/webapps/
	#sudo chown tomcat7 /var/lib/tomcat7/webapps/WebAPI.war -- restore once the default ubuntu tomcat7 is re-installed (with proper port conf)
	#sudo chgrp tomcat7 /var/lib/tomcat7/webapps/WebAPI.war -- restore once the default ubuntu tomcat7 is re-installed (with proper port conf)
	sudo /var/lib/tomcat7-DIKB/bin/startup.sh
	#sudo service tomcat7 start -- restore once the default ubuntu tomcat7 is re-installed (with proper port conf)

git-push:
	git push myfork master

test:
	wget -O tests/source.json "http://localhost:8090/WebAPI/source/sources" # -- change the port if using a differently configured tomcat

