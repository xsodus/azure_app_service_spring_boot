```markdown
## README.md

### Project Setup and Deployment on Azure App Service

This guide will help you set up and deploy your Spring Boot project on Azure App Service.

### Prerequisites

- Java 17
- Maven
- Docker
- Azure CLI
- An Azure account

### Project Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/xsodus/your-repo.git
   cd your-repo
   ```

2. **Build the project:**
   ```sh
   ./mvnw clean package
   ```

3. **Create a Docker image:**
   ```sh
   docker build -t your_spring_boot_image:latest .
   ```

### Docker Compose Configuration

Ensure your `docker-compose.yml` file is configured correctly:

```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: mysql_container
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: mydatabase
      MYSQL_USER: myusername
      MYSQL_PASSWORD: mypassword
    ports:
      - "3306:3306"
    volumes:
      - ./data:/var/lib/mysql

  rest_api:
    image: openjdk:17-jdk
    container_name: rest_api_container
    ports:
      - "80:8080"
    depends_on:
      - mysql
    volumes:
      - ./target:/target
    command: [ "java", "-jar", "/target/backend-0.0.1-snapshot.jar" ]
```

### Deploying to Azure App Service

1. **Login to Azure:**
   ```sh
   az login
   ```

2. **Create a resource group:**
   ```sh
   az group create --name myResourceGroup --location eastus
   ```

3. **Create an App Service plan:**
   ```sh
   az appservice plan create --name myAppServicePlan --resource-group myResourceGroup --sku B1 --is-linux
   ```

4. **Create a Web App:**
   ```sh
   az webapp create --resource-group myResourceGroup --plan myAppServicePlan --name myUniqueAppName --deployment-container-image-name your_spring_boot_image:latest
   ```

5. **Configure the Web App to use Docker:**
   ```sh
   az webapp config container set --name myUniqueAppName --resource-group myResourceGroup --docker-custom-image-name your_spring_boot_image:latest
   ```

6. **Deploy the Docker image to Azure Container Registry (ACR):**
   ```sh
   az acr create --resource-group myResourceGroup --name myACR --sku Basic
   az acr login --name myACR
   docker tag your_spring_boot_image:latest myacr.azurecr.io/your_spring_boot_image:latest
   docker push myacr.azurecr.io/your_spring_boot_image:latest
   ```

7. **Update the Web App to use the image from ACR:**
   ```sh
   az webapp config container set --name myUniqueAppName --resource-group myResourceGroup --docker-custom-image-name myacr.azurecr.io/your_spring_boot_image:latest
   ```

### Rolling Back the Deployment with Azure Container Registry (ACR)

To roll back the deployment to a previous version of your Docker image in Azure App Service, follow these steps:

1. **List the available tags in your ACR:**
   ```sh
   az acr repository show-tags --name myACR --repository your_spring_boot_image
   ```

2. **Update the Web App to use the previous image tag:**
   ```sh
   az webapp config container set --name myUniqueAppName --resource-group myResourceGroup --docker-custom-image-name myacr.azurecr.io/your_spring_boot_image:<previous_tag>
   ```

Replace `<previous_tag>` with the tag of the previous version you want to roll back to.

### Example

If you want to roll back to the tag `v1.0.0`, you would run:
```sh
az webapp config container set --name myUniqueAppName --resource-group myResourceGroup --docker-custom-image-name myacr.azurecr.io/your_spring_boot_image:v1.0.0
```

This will update your Azure App Service to use the specified previous version of your Docker image.

### Deploying with `mvn azure-webapp:deploy` Command

To deploy your Spring Boot project to Azure App Service using the `mvn azure-webapp:deploy` command, follow these steps:

1. **Add the Azure Web App Maven Plugin to your `pom.xml`:**

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.microsoft.azure</groupId>
            <artifactId>azure-webapp-maven-plugin</artifactId>
            <version>1.14.0</version>
            <configuration>
                <resourceGroup>myResourceGroup</resourceGroup>
                <appName>myUniqueAppName</appName>
                <region>eastus</region>
                <pricingTier>B1</pricingTier>
                <runtime>
                    <os>linux</os>
                    <javaVersion>Java 17</javaVersion>
                    <webContainer>Java SE</webContainer>
                </runtime>
                <deployment>
                    <resources>
                        <resource>
                            <directory>${project.basedir}/target</directory>
                            <includes>
                                <include>*.jar</include>
                            </includes>
                        </resource>
                    </resources>
                </deployment>
            </configuration>
        </plugin>
    </plugins>
</build>
```

2. **Deploy the application using Maven:**

```sh
mvn clean package azure-webapp:deploy
```

This will package your Spring Boot application and deploy it to Azure App Service using the specified configuration in the `pom.xml` file.
### Accessing the Application

Once the deployment is complete, you can access your application at `http://myUniqueAppName.azurewebsites.net`.

### Conclusion

You have successfully set up and deployed your Spring Boot project on Azure App Service.
```