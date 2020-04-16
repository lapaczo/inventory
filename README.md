### Initial setup
* Application uses MariaDB database, so by default it requires:
    * Running MariaDB on host and port: "**localhost:3306**",
    * Database called: "**inventory**",
    * Database user with credentials: "**inventory/inventory**" and access to inventory database
    * Creation of the database and the user can be done by execution of following SQL script:
    `CREATE DATABASE inventory DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_unicode_ci;
     GRANT ALL PRIVILEGES ON inventory.* to inventory@'%' IDENTIFIED BY 'inventory';`
    
* All database settings can be reconfigured with environment variables:
    * Initially it tries to connect to following destination:
        * url: `"jdbc:mariadb://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_DB:inventory}"`
    * with following credentials:
        * username: `${DB_USERNAME:inventory}`
        * password: `${DB_PASSWORD:inventory}`

### Running tests
* For running application from command line please use: `gradlew clean test`

### Running application
* For running application from command line please use: `gradlew bootRun`

### Documentation
* REST API documentation is available on following address:
    * http://localhost:8080/swagger-ui.html#/
