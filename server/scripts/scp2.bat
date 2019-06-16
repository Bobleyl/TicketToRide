scp -i ticket-to-ride.pem ../../plugins/flatfile/target/FlatFilePlugin-1.0.jar ec2-user@ec2-18-224-234-208.us-east-2.compute.amazonaws.com:~/plugins/FlatFile.jar
scp -i ticket-to-ride.pem ../../plugins/relational/target/RelationalPlugin-1.0.jar ec2-user@ec2-18-224-234-208.us-east-2.compute.amazonaws.com:~/plugins/Relational.jar
scp -i ticket-to-ride.pem ../target/TicketToRide-1.0-jar-with-dependencies.jar ec2-user@ec2-18-224-234-208.us-east-2.compute.amazonaws.com:~
