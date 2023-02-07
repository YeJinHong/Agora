pipeline 
{
	agent any

	tools {			 
		gradle 'gradle 6.8'
	}

	environment {
		PROJECT = 'agora'
		APP_API = 'module-api'
		APP_CHAT = 'module-chat'
		APP_KURENTO = 'module-kurento'
	}

	stages {
		stage('Environment') {
			steps {
				echo 'Environment Settings Start'
				sh 'docker-compose -f env-config/docker-compose-env.yml up -d'
				echo 'Environment Settings End'
			}
		}
	}
}
