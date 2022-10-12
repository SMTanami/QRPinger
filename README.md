# Goal

The goal of this application is to be able to ping or message users via their assigned QR code. The ‘pinger’ scans the QR code and has the option of adding a message to the ping. Once customized, the pinger submits the ping to the system. The receiving user is notified of the ping on their smart device and the optional message entered by the pinger is displayed if one was entered.

### MVP

An application that allows a pinger to scan a QR code and a user to receive a notification that their code has been scanned. Pings should be throttled to up to one ping per five minutes.

### Stack

1. PostgreSQL - Data storage
2. ZXing - QR Java Library (https://github.com/zxing/zxing)
3. Spring / Spring Boot - API Development
4. Twilio - SMS notifications
5. Gradle - Build automation / Dependency handler

## Howto

### OpenAPI
[OpenAPI UI - Explore and send requests](http://localhost:8080/swagger-ui.html)

[OpenAPI Spec](http://localhost:8080/v3/api-docs/)


### Create a user
```bash
curl -X POST http://localhost:8080/user/signup \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Zaphond","lastName":"Beeblebrox", 
        "email":"zaphod.b@mail.fictional.bogus", 
        "phoneNumber":"5555551212"}' 
```
  
### Ping a User
```bash
curl -H "Content-Type: application/json" \
  -X GET http://localhost:8080/user/{USER_ID} 
```