compile:
	mvn clean package -s WebAPIConfig/settings.xml -P webapi-postgresql-dikb

package: compile
	mvn package -s WebAPIConfig/settings.xml -P webapi-postgresql-dikb

deploy: compile
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

test: 	# change the port if using a differently configured tomcat	
	wget -O tests/source.json "http://localhost:8090/WebAPI/source/sources"

test-dikb-calls:
	wget -O tests/assertion.json "http://localhost:8090/WebAPI/DIKB/POSTGRES-DIKB/assertion"
	wget -O tests/drug.json "http://localhost:8090/WebAPI/DIKB/POSTGRES-DIKB/drug"
	wget -O tests/precipitant-ketoconnazole.json "http://localhost:8090/WebAPI/DIKB/POSTGRES-DIKB/precipitant/ketoconazole"
	wget -O tests/recent-5.json "http://localhost:8090/WebAPI/DIKB/POSTGRES-DIKB/recent/5"
	wget -O tests/search-evidence-by-precipitant.json "http://localhost:8090/WebAPI/DIKB/POSTGRES-DIKB/search/increases/aripiprazole/pubmed"
	wget -O tests/evidence-type-by-drugname.json "http://localhost:8090/WebAPI/DIKB/POSTGRES-DIKB/evidencetype/escitalopram/aripiprazole"
	wget -O tests/druginfo-by-name.json "http://localhost:8090/WebAPI/DIKB/POSTGRES-DIKB/drugInfo/clopidogrel"

test-mp-calls:
	wget -O tests/get-all-drugnames-as-precipitant.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/drugname/precipitant"
	wget -O tests/get-all-drugnames-as-object.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/drugname/object"
	wget -O tests/get-all-drugnames.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/drugname/any"

	wget -O tests/get-second-drug-as-precipitant.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/drugname2/rxnorm-4719/precipitant"
	wget -O tests/get-second-drug-as-object.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/drugname2/rxnorm-4719/object"
	wget -O tests/get-second-drug.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/drugname2/rxnorm-4719/any"

	wget -O tests/get-method-by-two-drugs-precipitant.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/method/rxnorm-6135/rxnorm-2556/precipitant"
	wget -O tests/get-method-by-two-drugs-object.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/method/rxnorm-6135/rxnorm-2556/object"
	wget -O tests/get-method-by-two-drugs.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/method/rxnorm-6135/rxnorm-2556/any"

	wget -O tests/get-claim-by-method-and-drugs-precipitant.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/search/Ketoconazole/Citalopram/DDI-clinical-trial/precipitant"
	wget -O tests/get-claim-by-method-and-drugs-object.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/search/Ketoconazole/Citalopram/DDI-clinical-trial/object"
	wget -O tests/get-claim-by-method-and-drugs.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/search/Ketoconazole/Citalopram/DDI-clinical-trial/any"

	wget -O tests/get-case-report-by-method-and-drugs.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/search/Gemfibrozil/Warfarin/Case-Report/any"

	wget -O tests/get-all-claims.json "http://localhost:8090/WebAPI/mpevidence/POSTGRES-DIKB/claim"
