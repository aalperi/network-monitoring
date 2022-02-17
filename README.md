# Network monitoring

This is a network monitoring solution with speedtest-cli, springboot and Heroku.
Instructions are for Heroku free tier hobby, which is suitable for most purposes.

Monitor network performance with the help of speedtest-cli. Write results to postgresql database and deploy a small springboot webapp to view results.

Network monitoring can be done either in Unix or Windows environment.

## Dependencies

- Heroku account - Heroku: Cloud Application Platform https://www.heroku.com
- Postgres in Heroku - Heroku Postgres https://www.heroku.com/postgres
- Git - https://git-scm.com/
- Heroku-cli - The Heroku CLI https://devcenter.heroku.com/articles/heroku-cli
- speedtest-cli - https://github.com/sivel/speedtest-cli
- psql - https://www.postgresguide.com/utilities/psql/

## TODO
- change Google Charts to Highcharts
- enhancement for scaling for graph

### How to utilize this application

#### 1 - Clone this project

Install git: https://git-scm.com/

    git clone https://github.com/aalperi/network-monitor.git 

#### 2 - Initialize Heroku application and postgres db

This can be done multiple ways. 

You can login to Heroku and create application from Heroku UI:

https://dashboard.heroku.com/apps -> New -> Create New App. Give it a name (network-monitor) and choose your location. Create app.

or for example with the help of Git and Heroku-CLI:

    heroku create -a network-monitor

https://devcenter.heroku.com/articles/git

https://devcenter.heroku.com/articles/heroku-cli

Add postgres db to your Heroku application

From the UI under the application -> Configure addOns -> search for Heroku Postgres -> select plan Hobby Dev - Free -> Submit Order.

or with Heroku-CLI:

    heroku addons:create heroku-postgresql:hobby-dev

#### 3 - Create table to Heroku hosted postgres db

From UI, after database is created by Heroku, the Installed add-ons has link to database. Open Heroku Postgres to a new window -> Settings -> Database Credentials -> View Credentials -> URI.

To connect to Heroku hosted postgres db install psql.

    sudo apt-get install postgresql

Navigate to Heroku UI and AddOns to get database connection url.

    psql postgres://\<username>:\<password>@<host>/\<dbname>

Create a database with this SQL:

    REATE TABLE [IF NOT EXISTS] network_statistics (event_id serial PRIMARY KEY,timestamp TIMESTAMP,ping DECIMAL,download DECIMAL, upload DECIMAL,server_id VARCHAR(50),server_name VARCHAR(255), ip_address VARCHAR(50));

#### 4 - Install speedtest-cli

https://github.com/sivel/speedtest-cli

Test speedtest-cli with command 

    speedtest

#### 5 - Configure network-monitor.sh to connect to newly created postgres db

Edit network-monitor.sh script line 17. Replace [your postgresql connection] string with the database url you used to connect to database and create table.

Change network-monitor.sh to be executable file.

    chmod 755 network-monitor.sh

Test script running by

    bash network-monitor.sh

It should print the results and SQL. Check that SQL got inserted to postgres with

    SELECT * FROM network_statistics;

#### 6 - configure network-monitor.sh to be run periodically

On windows:
- open Scheduled tasks

On linux and macOS:

    crontab -e

Add a line without the hash (#) sing.

    */5 * * * * /path/to/the/network-monitor.sh

This will measure network performance every 5 minutes.

#### 7 - Deploy application to Heroku

Connect your Heroku repository to this application by

    heroku git:remote -a network-monitor

    git push heroku main

https://devcenter.heroku.com/articles/git

#### 8 - Monitor your network performance