# monitoring-made-easy-backend

docker build -t wimdk/logstash .
docker build -t wimdk/elasticsearch .
docker build -t wimdk/kibana .

docker run -d --name=elasticsearch -p 9200:9200 -p 9300:9300 -v <elasticsearch-config-dir>:/data wimdk/elasticsearch /elasticsearch/bin/elasticsearch -Des.config=/data/elasticsearch.yml
docker run -d --name=kibana -p 5601:5601 --link elasticsearch:es wimdk/kibana
docker run -d --name=logstash -v <logstash-config-dir>:/opt/logstash/conf.d --link elasticsearch:es wimdk/logstash agent
