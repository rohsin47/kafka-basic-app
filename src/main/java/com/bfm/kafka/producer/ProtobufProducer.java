package com.bfm.kafka.producer;

import com.bfm.kafka.config.AppConfig;

/**
 * @author rohsingh
 *
 */
public class ProtobufProducer {
	
	BasicProducer<byte[], byte[]> producer;
	
	public ProtobufProducer() {
		this.producer = new BasicProducer<byte[], byte[]>(AppConfig.getProducerConfig());
	}

}
