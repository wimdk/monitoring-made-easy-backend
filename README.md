# monitoring-made-easy-backend

	docker build -t wimdk/logstash .
	docker build -t wimdk/elasticsearch .
	docker builld -t wimdk/kibana .


 docker run -d --name=elasticsearch wimdk/elasticsearch
 docker run -d --name=kibana -p 5601:5601 --link elasticsearch:es wimdk/kibana
 docker run -d --name=logstash -v <logstash-config-dir>:/opt/logstash/conf.d --link elasticsearch:es wimdk/logstash agent
