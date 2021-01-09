## Spring k8s concourse

Project contains examples of:

- Spring boot dockerize with layering
- Deploy to k8s cluster
- CI with Concourse
- k8s readiness probe with spring actuator
- ~~Spring cloud gateway~~
- ~~Helm charts~~

## Build docker image

Prerequisites:
- docker
- maven

Then run `mvn spring-boot:build-image`. This will build layered docker image.

To fetch image with kubectl you have to push it to some docker registry.
To do so, you can specify you creds in spring-boot-maven-plugin configuration:

```xml
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <layers>
                        <enabled>true</enabled>
                    </layers>
                    <imageName>IMAGENAME</imageName>
                    <docker>
                        <publishRegistry>
                            <username>USERNAME</username>
                            <password>PASSWORD</password>
                            <url>URL</url>
                        </publishRegistry>
                    </docker>
                    <publish>true</publish>
                </configuration>
            </plugin>
```

Or you can push it manually using `docker push`


## Run with kubectl

Prerequisites:
- k8s cluster and kubectl (minikube or kind can be used)

Put url to your image in `deploy.yml -> spec.containers.image`. 

Then run `kubectl apply -f .k8s/deploy.yml`

## Concource CI

Prerequisites:
- installed Concource and cli fly (https://concourse-ci.org/docker-compose.yml) 

Put your credits in .ci/config.yml

Then run `fly -t $TARGET set-pipeline -p spring-boot-service -c .ci/pipeline.yml -l .ci/config.yml`

where:
- $TARGET is your target alias
- .ci/pipeline.yml is pipeline definition 
- .ci/config.yml is variables

Pipeline looks like this: test -> package -> deploy to k8s