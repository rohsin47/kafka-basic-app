package com.bfm.kafka.consumer;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rohsi
 *
 */
public class BasicConsumer<K extends Serializable, V extends Serializable> {
	
	private final Logger logger = LoggerFactory.getLogger(BasicConsumer.class);

	KafkaConsumer<K, V> consumer;
	
	public BasicConsumer(Properties props) {
		this.consumer = new KafkaConsumer<K, V>(props);
	}
	
	public void subscribe(String topic) {
		consumer.subscribe(Collections.singletonList(topic), new DefaultRebalanceListener());
	}
	
	public void subscribe(Collection<String> topics) {
		consumer.subscribe(topics, new DefaultRebalanceListener());
	}
	
	public ConsumerRecords<K, V> poll(long millis) {
		return consumer.poll(Duration.ofMillis(millis));
	}
	
	public void close() {
		try {
			consumer.close();
		} catch (Exception e) {
			logger.error("Exception occurred while stopping the producer", e);
		}
	}
	
	public void commitSync(boolean isSync) {
		if(isSync) {
			consumer.commitSync();
		} else {
			consumer.commitAsync();
		}
				
	}
	
	private static class DefaultRebalanceListener implements ConsumerRebalanceListener {
        @Override
        public void onPartitionsRevoked(Collection <TopicPartition> partitions) {
            System.out.println("Called onPartitionsRevoked with partitions:" + partitions);
        }

        @Override
        public void onPartitionsAssigned(Collection <TopicPartition> partitions) {
            System.out.println("Called onPartitionsAssigned with partitions:" + partitions);
        }

    }
}
