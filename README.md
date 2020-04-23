# Java Spring Boot project with embedded ActiveMQ JMS provider & distributed JTA transactions manager
Spring Boot with embedded JMS & JTA transactions

## Testing
There are several endpoints exposed to test transactions from different perspectives. By default project should be configured to not use JTA transaction management (spring-boot-starter-jta-atomikos dependency is not active).
In that case Spring Framework uses it's own local declarative transactions.
Lets call following endpoints in browser to create users user01, user02 and user03:
* http://localhost:8081/users/create?username=user01
* http://localhost:8081/users/transactional/create?username=user02
* http://localhost:8081/users/transactional/with_clone/create?username=user03
* http://localhost:8081/users/all

Last query "users/all" will print all users inserted to the database:
```
user01 : user02 : user03_clone : user03
```
In logs you will also see that JMS consumer has received some messages:
```
##################################
: received payload: <JMS received User : user01>
##################################
: received payload: <JMS received User : user02>
##################################
: received payload: <JMS received User : user03_clone>
##################################
: received payload: <JMS received User : user03>
##################################
```
As you see operation "transactional/with_clone/create" inserts 2 records. It will be used later to demonstrate data rollback.
Now let's modify our queries to not provide any usernames for new users. Which should lead database insertion errors because field username must not be null.

* http://localhost:8081/users/transactional/create
* http://localhost:8081/users/transactional/with_clone/create
* http://localhost:8081/users/all

