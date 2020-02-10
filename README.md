# PubSub example with Redis

* **ms-redis-publisher** is a microservice to publish a message to a Redis topic
* **ms-redis-subscriber** is a microservice to receive messages from a Redis topic

## Steps: 

* ### Run redis on your local machine
        docker run -d -p 6379:6379 redis

* ### Run the publisher microservice

* ### Run the subscriber microservice

* ### In publihser, call the /publish endpoint with desired message
    use **http://localhost:8080/swagger-ui.html** or Postman (or any other tool)

* ### In subscriber, call the /message endpoint to see received messages
    use **http://localhost:8081/swagger-ui.html** or Postman (or any other tool)

