apiVersion: apps/v1
kind: Deployment
metadata:
  labels:
    app: crud
  name: crud
  namespace: default
spec:
  selector:
    matchLabels:
      app: crud
  strategy:
    rollingUpdate:
      maxSurge: 25%
      maxUnavailable: 25%
    type: RollingUpdate
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: crud
    spec:
      containers:
      - args:
        - --http_port=80
        - --backend=127.0.0.1:80
        - #--service=banqregistration.endpoints.qpathways-dev.cloud.goog
        - --rollout_strategy=managed
        - -z
        - healthz
        image: gcr.io/endpoints-release/endpoints-runtime:1
        imagePullPolicy: IfNotPresent
        name: esp
        ports:
        - containerPort: 8080
          protocol: TCP
        readinessProbe:
          failureThreshold: 3
          httpGet:
            path: /healthz
            port: 8080
            scheme: HTTP
          periodSeconds: 10
          successThreshold: 1
          timeoutSeconds: 1
        resources: {}
        terminationMessagePath: /dev/termination-log
        terminationMessagePolicy: File
#      - env:
#        - name: BANQ_DB_HOST
#          valueFrom:
#            configMapKeyRef:
#              key: BANQ_DB_HOST
#              name: banqjava-config
        
        image: gcr.io/GOOGLE_CLOUD_PROJECT/github.com/murageshyadwad/java-service:COMMIT_SHA
        imagePullPolicy: IfNotPresent
        name: crud-sha256
        resources: {}
        terminationMessagePath: /dev/termination-log
        terminationMessagePolicy: File
      dnsPolicy: ClusterFirst
      restartPolicy: Always
      schedulerName: default-scheduler
      securityContext: {}
      terminationGracePeriodSeconds: 30
status: {}
