# Social-media-site-backend-Springboot

## Configure
Create a ```application.yml``` or ```application-{PROFILE}.yml```file in the config directory. Where ```{PROFILE}``` is the current profile / environment.

#### Example: **`application-development.yml`**
```yaml
server:
  port: 8080

spring:
  data:
    mongodb:
      uri: # url to mongodb (Ex: mongodb://localhost:27017)
      database: social-media-site

```

## Run
```bash
./gradlew bootRun
```
