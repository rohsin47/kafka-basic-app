/**
 * 
 */
package com.bfm.kafka.protobuf;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;

/**
 * @author rohsingh
 *
 */
public class KafkaProtobufDeserializer<T extends MessageLite> implements Deserializer<T> {
	
	private final Parser<T> parser;

    public KafkaProtobufDeserializer(Parser<T> parser) {
        this.parser = parser;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            return parser.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            throw new SerializationException("Error deserializing from Protobuf message", e);
        }
    }

    @Override
    public void close() {
    }

}
