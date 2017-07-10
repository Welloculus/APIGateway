**Install and setup MongoDB for Welloculus**

**a. Install MongoDB**

Installation of MongoDB is a simple 4-step process:
1. Download required version of MongoDB from https://www.mongodb.com/download-center. For Welloculus, we have used MongoDB Community Server Version 3.4
2. Unzip downloaded file. MongoDB is self-contained and does not have any other system dependencies. It can be installed and executed from any folder
3. Create folder C:\data\db. This is default location where MongoDB stores data. This location can be customized using --dbpath option while executing mongod
4. Navigate to MongoDB\Server\3.4\bin folder. Open command prompt and run mongod.exe

MongoDB server is ready to use. You can connect to database from command line by running mongo.exe in another command prompt or use a client to connect to database.

For more information on installation, please refer https://docs.mongodb.com/v3.4/tutorial/install-mongodb-on-windows/ or https://docs.mongodb.com/v3.4/administration/install-on-linux/

**b. Setup dummy database for Welloculus**

There are n+1 databases to be setup for Welloculus, where n is the number of healthcare providers and +1 is for administrator or facilitator. Let us say we need to set up for single provider to start with, so there will be 2 databases, one for provider and one for facilitator.

1. Copy the database dump folder as it is in MongoDB bin folder such that you have bin\dump\provider1 and bin\dump\facilitator folders.
2. Navigate to MongoDB bin folder and open command prompt
3. Run `mongorestore --db provider dump\provider1`. This will create provider database with all required collections and their document formats
4. Run `mongorestore --db facilitator dump\facilitator`. This will create facilitator database with all required collections and their document formats
In case of linux, points 1 and 2 above are not needed. You only need to copy the dump folder anywhere and run commands 3 and 4 on terminal.

After these two collections are created, your MongoDB is ready to be used with Welloculus APIGateway project. All other projects that need data from database like Cockpit will communicate with APIGateway for fetching data using web services.
