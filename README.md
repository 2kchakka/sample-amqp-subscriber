Sample AMQP (Advanced Message Queuing Protocol) java client acting as a subscriber to RabbitMQ broker/queue. This sample project is built using TDD (Test Driven Development) approach (though the test cases are fake as of now).

Prerequisite
============
Prerequisite is to have a running instance of RabbitMQ server.

Clone the project
=================
	git clone https://github.com/2kchakka/sample-amqp-subscriber.git {project_dir}
	
Update properties file
======================
Update application.properties file:

	rabbit.server.host=<RabbitMQ server host name or IP>

How to build
============
Build using Apache Maven. The command is:<br/>

	cd {project_dir}
	mvn clean install

How to run
==========
Run as a standalone java program. The command is:<br/>

	cd {project_dir}
	java -jar target/amqp-subsciber-0.0.1-SNAPSHOT-jar-with-dependencies.jar (for linux)
                                   OR
	java -jar target\amqp-subscriber-0.0.1-SNAPSHOT-jar-with-dependencies.jar (for windows)