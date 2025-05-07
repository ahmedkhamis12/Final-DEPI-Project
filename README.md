
# Final-DEPI-Project

A complete DevOps CI/CD project that automates the deployment of a containerized Node.js and MongoDB application using Jenkins, Docker, Ansible, and Kubernetes. This project was developed as a capstone for the Digital Egypt Pioneers Initiative (DEPI), demonstrating end-to-end delivery and infrastructure automation on AWS.

---

## 🚀 Project Overview

This project features a RESTful Node.js application for user profile management, integrated with MongoDB. The DevOps workflow includes:

- Containerization with Docker
- Orchestration with Docker Compose (for local) and Kubernetes (for production)
- CI/CD automation using Jenkins
- Infrastructure provisioning and configuration using Ansible
- Deployment on AWS EC2 instances
- Unit testing and automated build pipeline

---

## 📁 Tech Stack

- **Backend**: Node.js, Express.js, MongoDB
- **Containerization**: Docker, Docker Compose
- **CI/CD**: Jenkins
- **Configuration Management**: Ansible
- **Container Orchestration**: Kubernetes
- **Cloud**: AWS EC2
- **Version Control**: Git, GitHub

---

## ⚙️ Features

- REST API for user profile management
- MongoDB integration with persistent storage
- Jenkins pipeline for:
  - Code checkout
  - Unit testing
  - Docker build & push
  - Deployment via Ansible and Kubernetes
- Infrastructure as Code (IaC) with Ansible playbooks
- Kubernetes YAML files for production-grade deployment

---

## 🧩 Project Structure

```
Final-DEPI-Project/
│
├── server.js                # Main Node.js app
├── Dockerfile               # Backend Docker configuration
├── docker-compose.yml       # Local multi-container setup
├── ansible/                 # Ansible playbooks and inventories
│   ├── install_dependencies.yml
│   ├── deploy_app.yml
│   └── inventory
├── jenkins/                 # Jenkins pipeline config (Jenkinsfile)
├── k8s/                     # Kubernetes manifests (Deployment, Service)
│   ├── deployment.yaml
│   └── service.yaml
├── test/                    # Unit tests
└── README.md
```

---

## 🧪 Running Locally (Docker Compose)

1. **Clone the repository**
   ```bash
   git clone https://github.com/ahmedkhamis12/Final-DEPI-Project.git
   cd Final-DEPI-Project
   ```

2. **Run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

3. **API is available at:**
   ```
   http://localhost:3000
   ```

---

## 🔄 CI/CD Pipeline Overview

1. **Push to GitHub**
2. **Jenkins pipeline** triggers:
   - Cloning the repo
   - Installing dependencies
   - Running unit tests
   - Building Docker image
   - Pushing image to DockerHub
3. **Ansible** provisions the EC2 environment
4. **Kubernetes** deploys the app using manifests

---

## 🧰 Deployment with Ansible

1. **Configure inventory**
   Update the IP address in `ansible/inventory`.

2. **Run playbooks**
   ```bash
   ansible-playbook -i ansible/inventory ansible/install_dependencies.yml
   ansible-playbook -i ansible/inventory ansible/deploy_app.yml
   ```

---

## ☸️ Kubernetes Deployment

1. **Apply manifests**
   ```bash
   kubectl apply -f k8s/deployment.yaml
   kubectl apply -f k8s/service.yaml
   ```

2. **Expose the service**
   ```bash
   kubectl port-forward svc/node-app-service 3000:3000
   ```

---

## ✅ Sample API Endpoints

- `GET /api/users` – Get all users  
- `POST /api/users` – Add a new user  
- `PUT /api/users/:id` – Update a user  
- `DELETE /api/users/:id` – Delete a user  

---



## 🏆 Achievements

- Deployed production-ready containerized application
- Automated full CI/CD workflow with Jenkins & Ansible
- Implemented scalable Kubernetes architecture
- Used AWS EC2 for hosting

---



---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
