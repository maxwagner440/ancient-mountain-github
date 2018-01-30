# Capstone Starter Project

## Database

Inside the `<project-root>/database/` directory you will find several SQL scripts (`.sql` files). These can be used to (re)build a PostgreSQL database for this project. 

```
Run in this order:

1. maxfitness.sql
2. update.sql
3. update2.sql
```


Each of the SQL scripts has a specific purpose as described below:

| File Name | Description |
| --------- | ----------- | 
| `maxfitness.sql` | This script is intended to be the initial setup for this application. |
| `update.sql` | This script is used as the first update. |
| `update2.sql` | This script is used as the second update. |


### Database Users

The database superuser (i.e. `postgres`) should only be used for database administration and should not be used by applications. As such, two database users are created for use by the application as described below:


## Spring MVC Configuration

### Datasource

A Datasource has been configured that can be injected into your DAO objects. It connects to the database using the `capstone_appuser` database user.

#### Database Transactions

The Datasource has been configured to disable autocommit behavior. Instead, database transactions can be managed by using the `@Transactional` annotation on Controllers that make database modifications.

### JSP

Spring has been configured to look for JSP files in the `<project-root>/src/main/webapp/WEB-INF/jsp/` directory.

## Web Resources

The following directories have been created for static web resource files:

| Directory | Description | 
| --------- | ----------- |
| `<project-root>/src/main/webapp/css/` | `.css` files go here |
| `<project-root>/src/main/webapp/img/` | image files (e.g. `.png`, `.jpg`, `.gif`) go here |
| `<project-root>/src/main/webapp/js/` | `.js` files go here |

### JQuery and Bootstrap

Minified versions of the JQuery Core and Validation libraries (including the "Additional Methods" library) have been included in the `<project-root>/src/main/webapp/js/` directory.

Minified versions of the Bootstrap CSS and Javascript files have been included  in the `<project-root>/src/main/webapp/css/` and `<project-root>/src/main/webapp/js/` directories respectively.

## Testing

### DAO Integration Tests

`com.techelevator.DAOIntegrationTest` has been provided for use as a base class for any DAO integration test. It initializes a Datasource for testing and manages rollback of database changes between tests. The following is an example of extending this class for writing your own DAO integration tests:

```
package com.techelevator;

import org.junit.Before;
import javax.sql.DataSource;

public class MyJdbcDaoIntegrationTest extends DAOIntegrationTest {

    private MyJdbcDao dao;
    
    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        dao = new MyJdbcDao(dataSource);
    }
    
    @Test
    public void do_that_thing() {
        // use the dao here to perform some kind of test
    }
}
```
