## Background

This demo repository shows how to use spring cloud contract to implement Consumer-Driven Contract Testing.

## Principle
![SCCT.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6e9876026dd14faf90161ceae9abd9be~tplv-k3u1fbpfcp-watermark.image)

## Setup

- A Github account and setting [SSH Key](https://github.com/settings/keys)
- Fork contract [fraud_contract](https://github.com/lynings/fraud-contract)
- Fork provider repository [fraud](https://github.com/lynings/fraud)
- Fork consumer repository [loan](https://github.com/lynings/loan)
  
Replace your username in `src/e2e/resources/application.yml`

```gradle
git://https://github.com/<username>/fraud-contract.git"
```

## Install

Execute the following command at the root of the project.

```
./gradlew clean build
```

## Usage

### Build project

```
./gradlew clean build
```

### API Document

- offline:  `build/docs/asciidoc/api-guide.html`
- online:  `http(s)://ip:port/docs/api-guide.html`
