/**
 * 
 */
package com.bfm.kafka.protobuf;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.google.protobuf.MessageLite;

/**
 * @author rohsingh
 *
 */
public class KafkaProtobufSerializer<T extends MessageLite> implements Serializer<T> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public byte[] serialize(String topic, T data) {
		return data.toByteArray();
	}

	@Override
	public void close() {
	}

}
