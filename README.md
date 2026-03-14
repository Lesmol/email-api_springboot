# Email API 📧

This Spring Boot application provides a microservice for sending automated, template-driven market update emails via Amazon Simple Email Service (SES).

## 🚀 Features

* **Template-Driven Emails**: Uses Thymeleaf to render professional HTML market reports.
* **Market Data Support**: Handles structured data for both Stocks (symbol, opening prices, and percentage changes) and Forex (ticker and opening price).
* **AWS SES Integration**: Specifically configured to utilize the AWS SDK for Java 2.x for reliable email delivery in the `af-south-1` region.
* **Serverless Ready**: Includes the AWS Lambda Web Adapter, allowing the application to run as a containerized Lambda function.

## 💻 Tech Stack

* **Java**: 25.
* **Framework**: Spring Boot 4.0.3.
* **Cloud Services**: AWS SES (Region: af-south-1).
* **Build Tool**: Maven.
* **Containerization**: Docker (Amazon Corretto 25 on AL2023).

## 📡 API Endpoints

### Send Market Update 📊
Triggers a market report email to a list of recipients.

* **URL**: `/api/v1/send-market-update`.
* **Method**: `POST`.
* **Payload Example**:
    ```json
    {
      "stocks": [
        {
          "symbol": "UBER",
          "open": "75.40",
          "pastOpen": "70.20",
          "percentageDifference": "12.2"
        }
      ],
      "forex": {
        "ticker": "USDZAR",
        "openingPrice": 18.45
      },
      "recipients": [
        "molemilesedi@gmail.com"
      ]
    }
    ```

## ⚙️ Configuration and Deployment

### Environment Variables 🔑
The application requires AWS credentials and configuration to interact with SES:
* `AWS_ACCESS_KEY_ID`
* `AWS_SECRET_ACCESS_KEY`
* `PORT` (Defaults to `8080`)

### Docker Build 🐳
The project is containerized using a multi-stage-ready Dockerfile.
```bash
docker build -t email-api .
```

### CI/CD 🛠️
A GitHub Actions workflow is provided to automatically build the project and push the resulting image to Amazon ECR upon a manual trigger from the `develop` branch.
```yaml
on:
  workflow_dispatch:
```

[![My Skills](https://skillicons.dev/icons?i=aws,terraform,java,spring,git,githubactions,html,css&perline=4)](https://skillicons.dev)
