#!/bin/bash


if [[ -z $1 ]]
then
echo "usage : ./insertDummyData.sh {hostname} "
exit  0
fi

while true
do
	echo "Press [CTRL+C] to stop.."
	sleep $[ ( $RANDOM % 10 )  + 1 ]s
	RANDOMWORD=`curl http://randomword.setgetgo.com/get.php`
	wget -qO- http://$1:8888/MetricsDropwizardSpike/dummyservice/echo/$RANDOMWORD\r\n
	echo "sending $RANDOMWORD"
done
