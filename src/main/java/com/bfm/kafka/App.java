/**
 * 
 */
package com.bfm.kafka;

import com.bfm.kafka.consumer.ProtobufConsumer;
import com.bfm.kafka.producer.ProtobufProducer;

/**
 * @author rohsi
 *
 */
public class App {
	
	public static void main(String[] args) {
		ProtobufProducer producer = new ProtobufProducer();
		ProtobufConsumer consumer = new ProtobufConsumer();
	}

}
