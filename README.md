RESTful-Phone-Book
==================
The purpose of this project is to demonstrate a basic CRUD application with SmartGWT RestDataSources and back-end 
Spring MVC Controllers which are demonstrating RESTful web-services.   

This project could be broken down into two parts:

a) a front-end with SmartGWT, or JQuery and JSP Pages, or JSP Pages with Node.js, or backbone.js, or GWT.

b) a back-end that contains the database entities, the DAO's, the DTO's, and the Spring MVC Controllers, 
hence a generic back-end that can be used with any front-end.

Since I have a lot of experience with SmartGWT, my first projects used GWT-RPC from the back-end to do the CRUD work.
However, per the documentation from the SmartGWT web-site, the preferred use is a generic RESTful web-service back-end.
It's been my experience, that when you use a GWT-RPC system to facilitate data from the back-end to the front-end, then 
your clients must know how to use GWT-RPC and that can be limiting.  You don't know what future systems may decide to
use this back-end, so by using a generic Spring RESTful web-services, the options from the front-end become more varied.

The stack that I am using here can be inferred from looking at the pom.xml, but I will still outline it here:
Java 1.6 - haven't switched over to Java 1.7 yet, but I will be soon.

Spring 3.2 for all modules, it is said that Spring 3.2 already contains support for Jackson2 Mapper.

Hibernate/JSP 4.1.4.Final

SmartGWT 2.4

Jackson2 - 2.1.4, which was the latest/greatest at the time of this update.
