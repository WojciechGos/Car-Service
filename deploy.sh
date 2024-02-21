#!/bin/bash
# be careful its not working perfectly fine 
# its better to not use it at all 
read -p "Dockerhub Username: " DOCKERHUB_NAME
read -s -p "Dockerhub Password: " DOCKERHUB_PASS

[ -z "$DOCKERHUB_NAME" ] && echo "Missing required Dockerhub Username"  && exit 1
[ -z "$DOCKERHUB_PASS" ] && echo "Missing required Dockerhub Password"/  && exit 1

ibmcloud ce project create -n "Car service"
ibmcloud ce project select -n "Car service"
id=$(ibmcloud ce proj current | grep "Context:" | awk '{print $2}')
ibmcloud ce registry create -n "${DOCKERHUB_NAME}-dockerhub" -u $DOCKERHUB_NAME -p $DOCKERHUB_PASS -s https://index.docker.io/v1/


# Car service
ibmcloud ce build create -n car-service-build -i ${DOCKERHUB_NAME}/car-service:latest --src https://github.com/WojciechGos/Car-Service --rs "${DOCKERHUB_NAME}-dockerhub" --cdr CarService --sz small
ibmcloud ce buildrun submit -b car-service-build -n car-service-buildrun -w
ibmcloud ce app create -n car-service -i ${DOCKERHUB_NAME}/car-service:latest --cl -p 5001 --min 1 --cpu 0.25 -m 0.5G -e LOG_LEVEL=info


#frontend
ibmcloud ce build create -n ui-build -i ${DOCKERHUB_NAME}/ui:latest --src https://github.com/WojciechGos/Car-Service --rs "${DOCKERHUB_NAME}-dockerhub" --cdr frontend --sz small
ibmcloud ce buildrun submit -b ui-build -n ui-buildrun -w
ibmcloud ce app create -n frontend -i ${DOCKERHUB_NAME}/frontend:latest -p 3000 --min 1 --cpu 0.25 -m 0.5G -e NODE_ENV=production -e CAR_SERVICE_URL=http://carservice.${id}.svc.cluster.local
