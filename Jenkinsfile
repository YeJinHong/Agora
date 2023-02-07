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
		stage('environment') {
			when {
				branch 'main'
				changeset "env-config/**/*"
			} 
			steps {
				echo 'Environment Settings Start'
				sh 'docker-compose -f env-config/docker-compose-env.yml up -d'
				echo 'Environment Settings End'
			}
		}
		stage('build-module-api') {
			when {
				branch 'main'
				anyOf {
					changeset "backend/module-core/**/*"
					changeset "backend/module-api/**/*"
				}
			}
			steps {
				echo 'Build Start "${APP_API}"'
				sh 'backend/gradlew ${APP_API}:build -x test'
				echo 'Build End "${APP_API}"'
			}
		}
		stage('build-module-chat') {
			when {
				branch 'main'
				changeset "backend/module-chat/**/*"
			}
			steps {
				echo 'Build Start "${APP_CHAT}"'
				sh 'backend/gradlew ${APP_CHAT}:build -x test'
				echo 'Build End "${APP_CHAT}"'
			}
		}
		stage('build-module-kurento') {
			when {
				branch 'main'
				changeset "backend/module-kurento/**/*"
			}
			steps {
				echo 'Build Start "${APP_KURENTO}"'
				sh './gradlew ${APP_KURENTO}:build -x test'
				echo 'Build End "${APP_KURENTO}"'
			}
		}
		
	}
}
