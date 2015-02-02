# Weblogic 12.1.3 - @Produces

This project reproduces the issue on Weblogic 12.1.3 Update1 with injection of produced beans into JAX-RS resources.

Run with `mvn clean test -P {application server profile}`

Available profiles: glassfish, wildfly and weblogic. Each is a remote profile (so an installed application server is required for running the tests). Glassfish and Wildfly are for reference purposes only.

This projects mimics the behaviour of trying to inject an external (non-cdi) library, like in this example: https://github.com/kubamarchwicki/jOOQ-javaee-example
