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

		stage('build-module-api') {
			when {
				anyOf {
					changeset "backend/module-api/**/*"
				}
			}
			steps {
				echo 'Build Start "${APP_API}"'
				sh 'chmod +x backend/gradlew'
				sh '''
					backend/gradlew -p backend/${APP_API} build -x test
				'''
				echo 'Build End "${APP_API}"'
			}
		}

		stage('deploy-module-api') {
			when {
				anyOf {
					changeset "backend/module-api/**/*"
				}
			}
			steps {
				echo 'Deploy Start "${APP_API}"'
				sh 'docker-compose -f backend/${APP_API}/docker-compose.yml down'
				sh 'docker-compose -f backend/${APP_API}/docker-compose.yml build --no-cache'
				sh 'docker-compose -f backend/${APP_API}/docker-compose.yml up -d'
				echo 'Deploy End "${APP_API}"'
			}
		}
	}
}
