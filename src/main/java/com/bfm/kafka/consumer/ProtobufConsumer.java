/**
 * 
 */
package com.bfm.kafka.consumer;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import com.bfm.kafka.protobuf.KafkaProtobufDeserializer;


/**
 * @author rohsi
 *
 */
public class ProtobufConsumer {
	
	BasicConsumer<byte[], byte[]> consumer;
	
	public ProtobufConsumer() {
		this.consumer = new BasicConsumer<byte[], byte[]>(getConfig());
	}
	
	public static Properties getConfig() {
		Properties props = new Properties();
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.setProperty("auto.offset.reset", "earliest");
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaProtobufDeserializer.class.getName());
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return props;
	}

}
