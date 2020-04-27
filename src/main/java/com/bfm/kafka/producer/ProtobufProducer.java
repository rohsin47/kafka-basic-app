package com.bfm.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import com.bfm.kafka.protobuf.KafkaProtobufSerializer;

/**
 * @author rohsingh
 *
 */
public class ProtobufProducer {
	
	BasicProducer<byte[], byte[]> producer;
	
	public ProtobufProducer() {
		this.producer = new BasicProducer<byte[], byte[]>(getConfig());
	}
	
	public Properties getConfig() {
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.setProperty(ProducerConfig.ACKS_CONFIG, "all");
		props.setProperty(ProducerConfig.RETRIES_CONFIG, "3");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaProtobufSerializer.class.getName());
		return props;
	}

}
