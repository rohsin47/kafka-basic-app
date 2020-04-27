/**
 * 
 */
package com.bfm.kafka.config;

/**
 * @author rohsi
 *
 */
public class KafkaConfig {
	
	private KafkaConfig( ) {
		//
	}
	
	// broker-topic-partition
    public static final String KAFKA_BROKERS = "localhost:9092";
    public static final String KAFKA_TOPIC = "my_topic";
    
    // producer
    public static final int KAFKA_PRODUCER_RETRIES = 0;
    public static final int KAFKA_PRODUCER_BATCH_SIZE = 16384;
    public static final int KAFKA_PRODUCER_LINGER_MS = 1;
    public static final int KAFKA_PRODUCER_BUFFER_MEMORY = 33554432;
    public static final String KAFKA_PRODUCER_ACKS = "all";
    public static final String KAFKA_PRODUCER_KEY_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String KAFKA_PRODUCER_VALUE_SERIALIZER  = "com.daniccan.kafka.PersonSerializer";
    public static final String KAFKA_PRODUCER_COMPRESSION_TYPE = "lz4";
    
    // consumer
    public static final String KAFKA_CONSUMER_KEY_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    public static final String KAFKA_CONSUMER_VALUE_DESERIALIZER = "com.daniccan.kafka.PersonSerializer";

}
