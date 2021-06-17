The database for this project can be accessed at https://phpmyadmin-c008.cloudclusters.net/index.php 
using the username tweetsuser and password tweetsuser1#. Optionaly you could run the database locally(MYSQL database)
and within the application.properties file change the spring.datasource.url property to the url of your local database
and spring.datasource.username and spring.datasource.password property with the username and password to the database.

If you will be using the local database you have the tweets.sql file to initialize your database with a few sample tweets.

Keep in mind that the default database is in the cloud, and it is not production database, so the performance cant be the same
as a local database(queries can take two or three seconds), but it should be a problem, and it is a lot easier than 
wasting time to setup a local database :) .