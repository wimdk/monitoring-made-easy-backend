FROM java:7-jre
MAINTAINER wimdk <wim@karrestraat.net>


RUN \
 cd /tmp && \
 wget https://download.elastic.co/kibana/kibana/kibana-4.0.2-linux-x64.tar.gz && \
 tar xvzf kibana-4.0.2-linux-x64.tar.gz && \
 rm -f kibana4.0.2-linux-x64.tar.gz && \
 mv /tmp/kibana-4.0.2-linux-x64 /kibana

RUN sed -i -e"s/^elasticsearch_url: \"http:\/\/localhost:9200\"/elasticsearch_url: \"http:\/\/es:9200\"/" /kibana/config/kibana.yml

 # Define mountable directories.
 VOLUME ["/data"]

 # Define default command.
  CMD ["/kibana/bin/kibana"]

 EXPOSE 5601