# Spring Boot Kafka Event-Driven Architecture

+ The Spring Boot Kafka project comprises three microservices: order-service, stock-service, and email-service.
+ Following the event-driven design pattern, these microservices are loosely coupled for flexible, independent operation.
+ Kafka, the chosen message broker, facilitates asynchronous communication among the microservices.
+ When a customer places an order, the order-service generates an OrderEvent, publishing it to a Kafka topic. Both stock-service and email-service, as distinct consumers, process events from this shared topic concurrently.

## Steps to Setup

**1. Download and Install Kafka**

Visit the Apache [Kafka download page](https://kafka.apache.org/downloads) to download the latest version of Kafka and extract it

**2. Start the ZooKeeper server**

Open a terminal and navigate to the Kafka directory.
```bash
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

**3. Start the Kafka server**

In a new terminal window, start the Kafka server:
```bash
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

**4. Clone the Application**

```bash
git clone https://github.com/attrayadas/springboot-kafka-event-driven-architecture
```

**5. Create MySQL Database**
```bash
create database order_db;
```

**6. Change MySQL username and password as per your installation**
+ open `stock-service/src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your MySQL installation

**7. Running the Microservices**

**7.1 Order Service:**

+ Navigate to the `order-service` directory.
+ Run the Spring Boot application to start the Order Service.

**7.2 Stock Service:**

+ Navigate to the `stock-service` directory.
+ Run the Spring Boot application to start the Stock Service.

**7.3 Email Service:**

+ Navigate to the `email-service` directory.
+ Run the Spring Boot application to start the Email Service.

**8. Sending OrderEvent to Kafka topic**

+ To publish a message use Postman (provide JSON in the request body):
```bash
POST http://localhost:8080/api/v1/orders
Content-Type: application/json

{
    "name": "HP Laptop order",
    "qty": 18,
    "price": 75000
}
```
**9. Read the events from the Kafka Topic**

To read events from the Kafka Topic, open a terminal and navigate to the Kafka directory. Then run:
```bash
.\bin\windows\kafka-console-consumer.bat --topic order_topics --from-beginning --bootstrap-server localhost:9092
```

**10. Read the events from the MySQL Database**

Check the order_events table in order_db database
```bash
SELECT * FROM order_db.order_events;
```
