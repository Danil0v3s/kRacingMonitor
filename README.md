# <img align="left" src="_imgs/compose-logo.svg" height=100> kRacing: iRacing data over the network

<!---freshmark shields
output = [
link(image('Circle CI', 'https://circleci.com/gh/diffplug/spotless/tree/main.svg?style=shield'), 'https://circleci.com/gh/diffplug/spotless/tree/main'),
link(shield('Live chat', 'gitter', 'chat', 'brightgreen'), 'https://gitter.im/{{org}}/{{name}}'),
link(shield('License Apache', 'license', 'apache', 'brightgreen'), 'https://tldrlegal.com/license/apache-license-2.0-(apache-2.0)')
].join('\n');
-->
[![License Apache](https://img.shields.io/badge/license-apache-brightgreen.svg)](https://tldrlegal.com/license/apache-license-2.0-(apache-2.0))
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Danil0v3s_kMonitor&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Danil0v3s_kMonitor)
[<img alt="Buy me a coffee!" width="170px" src="https://ko-fi.com/img/githubbutton_sm.svg" />](https://ko-fi.com/O4O8E3U08)
<!---freshmark /shields -->

### What is this?
![image](https://user-images.githubusercontent.com/13068064/181916509-d0e8811f-0387-4498-b5bb-787e1e8484c6.png)

A small desktop companion application to enable you to provide MSI Afterburner data to your network

### What does it have?
- :white_check_mark: A simple websocket server to stream your data over your local network
- :white_check_mark: More configuration options
- :white_check_mark: Registering as a service to start with Windows
- :soon: Desktop shortcut
- :soon: More robust API

### Getting started
1. Make sure you have MSI Afterburner up and running
2. Download the latest release, run it and start the server
3. Connect to the websocket using `ws://<your ip>/socket`
