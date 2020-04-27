package com.bfm.kafka.producer;

import java.io.Serializable;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rohsingh
 *
 */
public class BasicProducer<K extends Serializable, V extends Serializable> {

	private final Logger logger = LoggerFactory.getLogger(BasicProducer.class);

	private KafkaProducer<K, V> producer;
	private boolean syncSend;

	public BasicProducer(Properties producerConfig) {
		this(producerConfig, true);
	}

	public BasicProducer(Properties producerConfig, boolean syncSend) {
		this.syncSend = syncSend;
		this.producer = new KafkaProducer<K, V>(producerConfig);
	}

	public void send(String topic, V v) {
		send(topic, -1, null, v, new DefaultCallback());
	}

	public void send(String topic, K k, V v) {
		send(topic, -1, k, v, new DefaultCallback());
	}

	public void send(String topic, int partition, V v) {
		send(topic, partition, null, v, new DefaultCallback());
	}

	public void send(String topic, int partition, K k, V v) {
		send(topic, partition, k, v, new DefaultCallback());
	}

	public void send(String topic, int partition, K key, V value, Callback callback) {
		try {
			ProducerRecord<K, V> record;
			if (partition < 0) {
				record = new ProducerRecord<K, V>(topic, key, value);
			} else {
				record = new ProducerRecord<K, V>(topic, partition, key, value);
			}

			Future<RecordMetadata> future = producer.send(record, callback);
			if (!syncSend)
				return;
			future.get();
		} catch (Exception e) {
			logger.error("Error while producing event for topic : {}", topic, e);
		}
	}
	
	public void flush() {
		try {
			producer.flush();
		} catch (Exception e) {
			logger.error("Exception occurred while stopping the producer", e);
		}
	}

	public void close() {
		try {
			producer.close();
		} catch (Exception e) {
			logger.error("Exception occurred while stopping the producer", e);
		}
	}

	private class DefaultCallback implements Callback {

		public void onCompletion(RecordMetadata recordMetadata, Exception e) {
			if (e != null) {
				logger.error("Error while producing message to topic : {}", recordMetadata.topic(), e);
			} else
				logger.debug("sent message to topic:{} partition:{}  offset:{}", recordMetadata.topic(),
						recordMetadata.partition(), recordMetadata.offset());
		}
	}
}
