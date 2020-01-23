package com.qolsys.cassandra.consumers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Session;
import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.constants.Constants;
import com.qolsys.cassandra.db.EventEntityOperations;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
/**
 * @author suresh
 *
 */
public class LeshanEventsConsumer {

	private static Logger logger = LoggerFactory.getLogger(LeshanEventsConsumer.class);

	private ConsumerConnector consumerConnector = null;
	private final String topic = Constants.EVENTS_TOPIC;

	public void initialize() {
		logger.debug("Initializing java kafka leshan events consumer for topic ::" + topic);
		try{
			Properties props = new Properties();
			//props.put("zookeeper.connect", Constants.ZOOKEEPER_HOST);
			props.put("bootstrap.servers", Constants.BOOTSTRAP_SERVER_HOST);
			props.put("group.id", Constants.EVENTS_GROUP_ID);
			props.put("zookeeper.session.timeout.ms", Constants.ZOOKEEPER_SESSION_TIMEOUT);
			props.put("zookeeper.sync.time.ms", Constants.ZOOKEEPER_SYNC_TIME);
			props.put("auto.commit.interval.ms", Constants.AUTO_COMMIT_INTERVAL);
			props.put("auto.commit.enable", "false");

			ConsumerConfig conConfig = new ConsumerConfig(props);
			consumerConnector = Consumer.createJavaConsumerConnector(conConfig);
		}catch(Exception e){
			logger.error("Kakfka leshan events consumer failed :: ", e);
		}
	}

	public void consume() {
		try{
			Map<String, Integer> topicCount = new HashMap<String, Integer>();       
			topicCount.put(topic, new Integer(1));
			Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams =
					consumerConnector.createMessageStreams(topicCount);         
			List<KafkaStream<byte[], byte[]>> kStreamList = consumerStreams.get(topic);
			String jsonMsg = null;
			for (final KafkaStream<byte[], byte[]> kStreams : kStreamList) {
				ConsumerIterator<byte[], byte[]> consumerIte = kStreams.iterator();
				while (consumerIte.hasNext()){
					jsonMsg = new String(consumerIte.next().message());
					logger.info("Message consumed from topic [" + topic + "] : "   +
							jsonMsg);   
					System.out.println(jsonMsg);
					Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
					/*boolean status = EventEntityOperations.ingestJson(session, Constants.CASSANDRA_EVENTS_TABLE, jsonMsg);
					logger.info("Is inserted/updated into leshan events table :: "+status);*/
				}
			}
			if (consumerConnector != null)   
				consumerConnector.shutdown();      
		}catch(Exception e){
			logger.error("Unexpected error reported while consuming messages[LeshanEventsConsumer] :: ", e);
		}
	}

}
