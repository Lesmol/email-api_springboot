FROM amazoncorretto:25-al2023 AS build
WORKDIR /app

COPY --from=public.ecr.aws/awsguru/aws-lambda-adapter:1.0.0-rc1 /lambda-adapter /opt/extensions/lambda-adapter

COPY target/email-api-*.jar app.jar

EXPOSE 8080
ENV PORT=8080

ENTRYPOINT ["java", "-Xmx512m", "-jar", "app.jar"]