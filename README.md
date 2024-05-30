# Happy HTTP Mod

This Minecraft mod introduces two custom blocks that interact with webhooks and HTTP requests, enabling powerful integrations and automations both within and outside the Minecraft world.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)


## Introduction

**Happy http mod** allows you to connect your Minecraft world to your external world using HTTP requests. Can for example be used with home automation. Let's say you want a secret door in Minecraft to open when a a QR code is scanned, or when a motion sensor is triggered. Or you want to send an SMS when you enter a location in Minecraft, or trigger a redstone circuit. Only your imagination stops you from finding fun ways of using the happy http mod. 

### Usage

- **HTTP Receiver Block**: 
  - **Starting Automations in Minecraft from Outside Triggers**: Use the HTTP Receiver Block to initiate Minecraft automations based on external events. For example, trigger in-game events from a smart home system when motion is detected, or start a Minecraft mechanism when a specific condition is met on an external server or service.
  
- **HTTP Sender Block**: 
  - **Trigger Automation on APIs, Home Automation Systems, etc.**: Use the HTTP Sender Block to send HTTP requests to external APIs or services. For example, trigger actions on a home automation system, like turning on lights or unlocking doors, when a redstone signal is received, or send notifications or update external systems based on in-game events.

## Features

- **HTTP Receiver Block**: Sends a redstone signal when a webhook with the correct parameters is accessed. Ideal for starting automations in Minecraft from outside triggers.
- **HTTP Sender Block**: Sends an HTTP request to a specified URL with parameters when it receives a redstone signal. Perfect for triggering automation on APIs, home automation systems, and more.
- **Cross-Platform**: Integrated webhook server. Works on both Windows and Linux. It binds to the IP of the host machine on a configurable port.


## Installation

### Requirements

#### Client

- Minecraft version 1.20.2 (roadmap: 1.19.4, 1.19.2, 1.18.2)
- Forge version X.X.X
- Fabric version X.X.X

#### Server

- Minecraft Server version 1.20.2 (roadmap: 1.19.4, 1.19.2, 1.18.2)
- Minecraft Spigot Server version 1.20.2 (roadmap: 1.19.4, 1.19.2, 1.18.2)


### Steps

1. **Download the Mod**:
   - Download the latest release of the mod from the [Releases](https://github.com/clapters/happyhttpmod/releases) page.

2. **Install Minecraft Forge:**
   Download and install the appropriate version of Minecraft Forge from [Forge's official site](https://files.minecraftforge.net/).

3. **Add the Mod to Minecraft:**
   - Navigate to your Minecraft installation folder.
   - Open the `mods` folder (create it if it doesn't exist).
   - Place the downloaded mod `.jar` file into the `mods` folder.

4. **Launch Minecraft:**
   - Open the Minecraft Launcher.
   - Select the Forge profile.
   - Start the game.

## Usage

1. **Configure the Webhook Server**:
   - After the first run, a configuration file named `happyhttpmod-config.json` will be generated in the Minecraft configuration directory (usually `.minecraft/config/`).
   - Edit the `happyhttpmod-config.json` file to set up the webhook server's IP address and port:
     ```json
     {
       "webhookServerIP": "",
       "webhookServerPort": 4567
     }
     ```
   - If `webhookServerIP` is left blank or missing, the server will use the IP of the local machine. If your machine has multiple IP addresses, specify the desired IP address to bind the server to that specific IP.

2. **Place and Configure Blocks**:
   - **HTTP Receiver Block**: 
     - Place the HTTP Receiver Block in your Minecraft world.
     - Right-click the block to open its configuration interface.
     - Set up the parameters that the block should listen for when a webhook request is received, such as expected parameter keys and values.

   - **HTTP Sender Block**:
     - Place the HTTP Sender Block in your Minecraft world.
     - Right-click the block to open its configuration interface.
     - Set the target URL and parameters that the block should send when it receives a redstone signal.

3. **Set Up Redstone Circuits**:
   - Connect the HTTP Receiver Block to redstone dust and a redstone lamp or any other redstone mechanism.
   - Ensure the HTTP Sender Block is connected to a redstone input source (like a button or lever).

## Configuration
**Configure the Webhook Server**:
   - After the first run, a configuration file named `happyhttpmod-config.json` will be generated in the Minecraft configuration directory (usually `.minecraft/config/`).
   - Edit the `happyhttpmod-config.json` file to set up the webhook server's IP address and port:
     ```json
     {
       "webhookServerIP": "",
       "webhookServerPort": 4567
     }
     ```
   - If `webhookServerIP` is left blank or missing, the server will use the IP of the local machine. If your machine has multiple IP addresses, specify the desired IP address to bind the server to that specific IP.


## Contributing
Guidelines for contributing to the project.

- Fork the repository.
- Create your feature branch (git checkout -b feature/AmazingFeature).
- Commit your changes (git commit -m 'Add some AmazingFeature').
- Push to the branch (git push origin feature/AmazingFeature).
- Open a pull request.

## Support

If you encounter any issues or have questions, please open an issue on the [GitHub Issues](https://github.com/clapters/happyhttpmod/issues) page.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Enjoy automating your Minecraft world with webhooks and HTTP requests!
