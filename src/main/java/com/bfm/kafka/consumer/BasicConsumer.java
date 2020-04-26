package com.bfm.kafka.consumer;

import java.io.Serializable;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author rohsi
 *
 */
public class BasicConsumer<K extends Serializable, V extends Serializable> {

	KafkaConsumer<K, V> consumer;
	
	public BasicConsumer(Properties props) {
		this.consumer = new KafkaConsumer<K, V>(props);
	}
	
}
