pipeline {
    agent any
    environment {
        AWS_REGION = 'us-west-2'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/my-org/my-repo.git'
            }
        }
        stage('Terraform Init') {
            steps {
                sh 'terraform init'
            }
        }
        stage('Terraform Plan') {
            steps {
                sh 'terraform plan -var-file="environments/dev/terraform.tfvars"'
            }
        }
        stage('Terraform Apply') {
            steps {
                sh 'terraform apply -auto-approve -var-file="environments/dev/terraform.tfvars"'
            }
        }
    }
}
