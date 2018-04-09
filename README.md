# Camel-Test
Repo for testing apache camel



The files in src are for Java SE.

Camel-Test-Producer2 and Camel-Test-Consumer are for Java EE enviroment, where producer produces messages and sends them to a queue and consumer takes messages from said queue.

In short the EE part works by CDI injection to automaticly detect routes and provide a camel context. For more detailed  info regarding the how-part see CamelRouteBuilder.java in Camel-Test-Producer2.
