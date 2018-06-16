# mapping-framework

This framework is designed to allow support both to relational and document oriented databases for applications designed to handle documents. By building it in the shape of an integration system, we manage to provide to applications seamless read and write access to databases that are able to store documents.

Following the specificed steps we will show next in the usage example section, applications that manipulate complex data will not be tightly coupled with D.O. databases (the general choose of document-oriented aplications) instead, the transition from one storage technology to another (e.g. from D.O. databases to databases based on the relational model of data) could be accomplished without maintenance effort in the application/model layers.

## Getting started 

First of all, it is important to know the basic architecture of this mapping framework. We developed its code in Java and it is essentially divided in two layers: model and persistence (see the figure below).

<p style="text-align:center;">
<img src="framework-architecture.png" width="80%" />
</p>

Above the dotted line we isolate the generic behavior of the persistence logic to store and to manipulate documents-based schema (representing the model layer). In this context, the AbstractDAO class acts like an interface, providing the signatures of the CRUD operations accessible by the application layer. The another DAOs classes are responsible to establish the communication with the databases, using the resources of each specific  API (in this example we have used MySQL and MongoDB connectors).

Last but no least, below the dotted line, we have the DAO classes which are context-dependent of the schema to be mapped. That are the classes that need to be encoded by you, developer, to be consistent with the model layer.

### Usage example


