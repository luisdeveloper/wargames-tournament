# CI/CD

This project includes Jenkins pipelines used to automate:
- Artifact retrieval from GitHub Packages
- Docker image creation
- Containerized execution
- Database provisioning for production-like environments

The CI/CD configuration is optional and not required to run the application locally.

## Jenkins

### Build
- Jenkinsfile.build: Downloads the repository from the selected branch, runs unit and integration tests, packages the project, and uploads the artifact to GitHub Packages.

### Deployment
- Jenkinsfile.deploy.dev: Downloads the artifact and the Dockerfile, installs the artifact into a Docker container, and starts the container.

- Jenkinsfile.deploy.stage: Downloads the artifact and the Dockerfile, installs the artifact into a Docker container, and starts both the application container and a PostgreSQL container.


