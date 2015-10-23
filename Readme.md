DropWizard Maven Archetype
==========================

# To generate a DropWizard project, do the following:

    > mvn archetype:generate -DarchetypeCatalog=local

And fill in the group, artifact, version and base package.

The generated project should build out-of-the-box, but will not do anything particularly usefull. Modify the model file under 'jenerator/src/main/resources' to create a data model. Add reference data under 'service_api/src/main/resources/refdata'.

# What does this archetype do for you?

This archetype will create a DropWizard project, which is runnable and entirely model driven. A model, which is essentially a UML class diagram or E-R diagram, is read in and from this the following items are generated:

* The data model as Java classes.
* An API for a data access layer, defining only CRUD operations.
* An implementation of the data access layer on Hibernate, implementing CRUD.
* An API for a service layer, repeating the same CRUD operations as the data access layer.
* An implementation of the service layer as REST on Jersey.
* A top-level module to assemble and run the above as a DropWizard application.
* The database DDL for the model.
* The Hibernate mapping between the model and the database.
* A Bean validation configuration for the model.
* A test stack which can test the basic CRUD operations and valid/invalid examples.
* A remote client, which proxies the service interface.

The test stack applies some reasoning around the expected behaviour of CRUD operations to automatically test them. For example, if an item is created, you would expect to be able to retrieve it immediately afterwards. If an item is deleted, you would not expect to be able to retrieve it. In this way, all pairs of operations out of C, R, U, and D, are tried in all orders, and expectations applied to the results.

The data model also allows a base set of validations to be defined. These are validations that you would expect to always apply to the data model in all situations. The Bean Validation framework is used, so additional validations for particular states can be layered on top as required.

A base test data provider class is generated, and positive and negative examples may be added to this, in order to test the validation. This allows validation to be tested easily using a data driven approach.

In summary, the test stack tests the following:

* Basic CRUD operation against a generic in-memory implementation of the DAO.
* Serialization/deserialization of the data model through JSON.
* CRUD operations through the service layer.
* Validation when performing Create operations.
* Integration tests to test CRUD operations against the database, using the DAOs.
* Integration tests to test validation when performing Create operations against the database.
* A full-stack test, which tests the CRUD operations against the database using the service layer, and performing serialization/deserialization of the data model through JSON.

# So what is the benefit of this?

This archetype allows a back-end for webservices to be created and in a very short amount of time. It is possible to go from a sketch of a data model to a working service with a reasonably rich set of operations, backed by a database, and with a significant amount of testing already done, in under an hour. The real advantage of this is that it is extremely agile. A service can be sketched out quickly, and rapidly modified as it takes shape.

Another benefit of this approach is that because projects start out being generated from a standard set of templates, they tend to end up with code that follows the same patterns everywhere. This can make understanding of larger code bases easier, and avoids much of the inconsistency in style that is typical.

# How to get the best out of it:

Here is a sequence of stages that development using this archetype might follow:

#### 1 Work quickly with a seed project.
    
Define a model and reference data and generate and test the service. Only the model and reference data will be kept, code will be fully re-generate on every iteration. This is called a 'seed' project, because it is grown from seed each and every time.
    
#### 2 Work on the operations and data validation, keep generating the model.

Once the model begins to reach stability, you can start to think about adding custom operations other than CRUD such as finders and so on. You may also start to think about adding a little validation to the model. You might also not want to expose CRUD operations in the service layer, and want to delete some of them (and the tests against them). At this point, you may decide to start keeping the generated code, and turn some of the code generation off in order to modify it by hand.

Look in the 'jenerator/pom.xml' and the code generation modules are all listed there. You may typically comment out the ones for generating the service layer, the top module and the tests at this stage. Keep generating the model, but check all the code in.

#### 3 Evolve the code generation.
    
The generated code is intended to be production quality, and in every way as good as hand written code, including its formatting and Javadocs. Generated code is placed under 'src/generated' directories, but these should be checked into version control. Think of the code generator as a junior programming assistant that can type really fast.

The best way to work with the generated code, is to modify the templates to do what you want. This is extremely powerful because sometimes modifying one template will result in tens of Java classes being rewritten for you. To give an example, suppose you want to add a 'findAll' operation to all of your entities, this can be added to the service layer templates.

#### 4 Evolve the project away from the code generation.

Code generation can only go so far, but it can still be useful to work with generated code and hand-written code at the same time. For example, a project may be heavily customized and all code generation turned off, but then some new functionality is to be added. It can be useful to follow the same process as above for the new functionality to quickly sketch it out.

At this stage you may move the code from the 'src/generated' directories to 'src/main', then turn off compilation of 'src/generated'. Now the code generation can be turned back on again, and new code copied down as required.

#### EOL for code generation.

As a project evolves code generation may eventually become completely useless. This is acceptable, because the situation is now no different to a hand written project, but has the advantage that the effect of code generation will continue to be felt in the regularity of the code, and the high degree of testing.

A lot of power to modify the code quickly will be lost, so try to avoid reaching this point too quickly. As new capabilities are added to the code generator there will also be less need to evolve away from it.