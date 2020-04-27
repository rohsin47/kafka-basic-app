/**
 * 
 */
package com.bfm.kafka.protobuf;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;

/**
 * @author rohsi
 *
 */
public class KafkaProtobufSerde<T extends MessageLite> implements Serde<T> {
	
	 private final Serializer<T> serializer;
	 private final Deserializer<T> deserializer;
	 
	 public KafkaProtobufSerde(Parser<T> parser) {
	        serializer = new KafkaProtobufSerializer<>();
	        deserializer = new KafkaProtobufDeserializer<>(parser);
	    }


	public Serializer<T> serializer() {
		return serializer;
	}

	public Deserializer<T> deserializer() {
		return deserializer;
	}

}
