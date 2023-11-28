Guice: Inject narrow scope into wider scope
=

This is an example of how a narrow scope (here: `RequestScoped`) can be injected into a wider scope via `Provider` as
part of a Servlet.

Setup
-

Checkout the code and run

```
mvn clean install
```

Run
-

```
docker run -p 8080:8080 -v ./target/GuiceServletPlayground-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/servlet.war tomcat:9.0
```

Run `curl http://localhost:8080/servlet/api/message` or open http://localhost:8080/servlet/api/message in a Browser: You
see "Hello World! Hello World!" and the log shows that both requests to `Message` use the same UUID (initialised
when `Message` gets created), meaning that the same instance is being reused during the request. Also note the use
of `ServletScopes#transferRequest` that makes the request scoped `Message` available even in a separate thread.

Even when `MessageService` is annotated with `com.google.inject.Singleton` the application continues to behave the same.