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
