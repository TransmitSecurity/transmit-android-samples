# Detection and Response Example

This sample android app uses DRS service to initiate strong confidentional on which users use your app.

## Prerequisites

To integrate with Transmit Security, you'll need to obtain your client credentials from the [Admin Portal](https://portal.transmitsecurity.io/login/email). From Risk > Settings, you can obtain your client ID and client secret. These will be used to identify your app and generate authorization for requests to Transmit. [Detection and response: Get Started](https://developer.transmitsecurity.com/guides/risk/overview/).

## Instructions

To run the sample on your android device:  

1 - Configure your client credentials in the `App.java` file:
```bash
'clientId' # Client ID obtained from the Admin Portal
'baseUrl' # Server URL
```

2 - Build and run the application in Android Studio on your android device target.

3 - Set user id.

## What is DRS?

Detection and Response services are embedded into web and mobile apps to help enterprises confidently welcome trusted customers and keep the bad people out. Detect risk in customer interactions on digital channels, and enable informed identity and trust decisions across the consumer experience. This is done by seamlessly monitoring user interactions across multiple channels in real-time and executing a dynamic risk engine that continuously assesses risk, challenges risky users, and elevates trust. This is aimed at reducing unauthorized access while keeping a frictionless experience and low false-positive rates.<br><br>

## Author

Transmit Security, https://github.com/TransmitSecurity

## License

This project is licensed under the MIT license. See the LICENSE file for more info.
