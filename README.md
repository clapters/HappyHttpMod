# Happy HTTP Mod

This Minecraft mod introduces two custom blocks that interact with webhooks and HTTP requests, enabling powerful integrations and automations both within and outside the Minecraft world.

## Introduction

**httpAutomator** allows you to connect your Minecraft world to external systems using HTTP requests. This mod includes:

- **HTTP Receiver Block**: Sends a redstone signal when a webhook with the correct parameters is accessed. Ideal for starting automations in Minecraft from outside triggers.
- **HTTP Sender Block**: Sends an HTTP request to a specified URL with parameters when it receives a redstone signal. Perfect for triggering automation on APIs, home automation systems, and more.

### Use Cases

- **HTTP Receiver Block**: 
  - **Starting Automations in Minecraft from Outside Triggers**: Use the HTTP Receiver Block to initiate Minecraft automations based on external events. For example, trigger in-game events from a smart home system when motion is detected, or start a Minecraft mechanism when a specific condition is met on an external server or service.
  
- **HTTP Sender Block**: 
  - **Trigger Automation on APIs, Home Automation Systems, etc.**: Use the HTTP Sender Block to send HTTP requests to external APIs or services. For example, trigger actions on a home automation system, like turning on lights or unlocking doors, when a redstone signal is received, or send notifications or update external systems based on in-game events.

## Features

- **Cross-Platform**: The webhook server is integrated into the mod and works on both Windows and Linux. It binds to the IP of the host machine on a configurable port.

## Installation

1. **Download the Mod**:
   - Download the latest release of the mod from the [Releases](https://github.com/clapters/httpAutomator/releases) page.

2. **Add to Minecraft**:
   - Copy the downloaded `.jar` file to your Minecraft `mods` folder.

## Usage

1. **Configure the Webhook Server**:
   - After the first run, a configuration file named `httpAutomator-config.json` will be generated in the Minecraft configuration directory (usually `.minecraft/config/`).
   - Edit the `httpAutomator-config.json` file to set up the webhook server's IP address and port:
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

## Support

If you encounter any issues or have questions, please open an issue on the [GitHub Issues](https://github.com/clapters/httpAutomator/issues) page.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Enjoy automating your Minecraft world with webhooks and HTTP requests!
