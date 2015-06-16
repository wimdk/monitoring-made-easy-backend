# monitoring-made-easy-backend


* First build your docker-images. Execute these commands from the respective image-folders :
docker build -t wimdk/logstash .
docker build -t wimdk/elasticsearch .
docker build -t wimdk/kibana .


* Next you have to run the containers in the following order. Replace the config-dirs with an absolute path on your host : 


docker run -d --name=elasticsearch -p 9200:9200 -p 9300:9300 -v <elasticsearch-config-dir>:/data wimdk/elasticsearch /elasticsearch/bin/elasticsearch -Des.config=/data/elasticsearch.yml
-> elasticsearch standard ports are exposed to the host so you can run the REST api. You can add your own config via the mounted directory. We give the container a name for linking with it.

docker run -d --name=kibana -p 5601:5601 --link elasticsearch:es wimdk/kibana
-> kibana container is linked with elasticsearch. You have to use the es-alias because it is used in the Dockerfile. Port 5601 is exposed so you can use your browser on this port for visiting kibana-webapp


docker run -d --name=logstash -v /Users/wimdk/workspace/monitoringMadeEasy/monitoring-made-easy-backend/logstash-config:/opt/logstash/conf.d --link elasticsearch:es  -p 1514:1514 wimdk/logstash agent
-> logstash-container is linked with elasticsearch. You have to use the es-alias because it is used in the Dockerfile. Port 1514 is exposed as syslog input for incoming data-stream.


docker run -d --name=jetty -p 8888:8080 --link logstash:logstash -v /Users/wimdk/workspace/jettyWebapps:/var/lib/jetty/webapps jetty
-> The jetty-image is used to run a small webapp that puts dummy data via a  socketappender in logstash. The jetty-image used is the standard from the Docker registry. Just use docker pull jetty.