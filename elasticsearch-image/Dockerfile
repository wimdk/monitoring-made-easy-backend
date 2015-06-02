FROM java:7-jre
#FROM library/elasticsearch
MAINTAINER wimdk <wim@karrestraat.net>


RUN \
 cd /tmp && \
 wget https://download.elastic.co/elasticsearch/elasticsearch/elasticsearch-1.5.2.tar.gz && \
 tar xvzf elasticsearch-1.5.2.tar.gz && \
 rm -f elasticsearch-1.5.2.tar.gz && \
 mv /tmp/elasticsearch-1.5.2 /elasticsearch

 # Define mountable directories.
 VOLUME ["/data"]

 # Define default command.
  CMD ["/elasticsearch/bin/elasticsearch"]

 EXPOSE 9200
 EXPOSE 9300
