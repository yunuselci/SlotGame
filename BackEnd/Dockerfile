FROM maven:3.8.3

WORKDIR /backend

COPY . .

RUN mvn clean install -DskipTests

CMD [ "mvn", "spring-boot:run", "-DskipTests" ]