== Spring Boot - CXF Samples - Web Services

This demo shows how JAX-WS services and clients could use Spring Boot and 
GraalVM's native-image capabilities and be packaged as native executables.

Pre-requisites
---------------------------------------

GraalVM 20.3.0 or later distribution should be installed and pre-configured as 
default JVM runtime (using JAVA_HOME), see please instructions at [1].

The GraalVM's native-image tool should be installed, see please 
instructions at [2].

Building and running the demo using Maven
---------------------------------------

From the base directory of this sample (i.e., where this README file is
located), the pom.xml file is used to build and run the demo. 

Using either UNIX or Windows, build server:

  mvn spring-boot:run -Pserver

The server should be available at http://localhost:8080/cxf/Hello?WSDL and 
will now display the generated WSDL.

Than build the client in capturing mode (please note that the server has to be up and running):
  
  mvn -Pcapture  (from a second command line window)
  
Than build the client:
  
  mvn clean package -Pclient (from a second command line window)

This goal will produce 'bin/jaxws-spring-boot-client' executable (platform-dependent) 
which could be run right away: 

  On Windows: bin\jaxws-spring-boot-client.exe
  On Linux: ./bin/jaxws-spring-boot-client
