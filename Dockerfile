FROM maven:3-openjdk-8 as builder

WORKDIR /app

COPY . .
RUN mvn clean install

RUN mkdir /out
RUN unzip /app/yogh-explorer-server/target/explorer.war -d /out

FROM alpine

COPY --from=builder /out /web

