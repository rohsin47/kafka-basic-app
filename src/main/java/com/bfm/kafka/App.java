/**
 * 
 */
package com.bfm.kafka;

import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import com.bfm.kafka.config.KafkaConfig;
import com.bfm.kafka.consumer.BasicConsumer;
import com.bfm.kafka.producer.BasicProducer;

/**
 * @author rohsi
 *
 */
public class App {
	
	public static void main(String[] args) {
		
		////////////////////////////////////////  PRODUCER  ////////////////////////////////////////////
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.setProperty(ProducerConfig.ACKS_CONFIG, "all");
		props.setProperty(ProducerConfig.RETRIES_CONFIG, "3");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		BasicProducer<String, String> producer = new BasicProducer<String, String>(props);
		
		String[] sentences = new String[] {
                "beauty is in the eyes of the beer holders 6",
                "Apple is not a fruit 7",
                "Cucumber is not vegetable 8",
                "Potato is stem vegetable, not root 9",
                "Python is a language, not a reptile 10"
            };
		
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
            // Pick a sentence at random
            String sentence = sentences[random.nextInt(sentences.length)];
            // Send the sentence to the test topic
            long startTime = System.currentTimeMillis();
            producer.send(KafkaConfig.KAFKA_TOPIC, sentence);
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Sent this sentence: " + sentence + " in " + elapsedTime + " ms");

        }
        System.out.println("Done");
        producer.flush();
        producer.close();	
        
        
        //////////////////////////////////////// CONSUMER  ////////////////////////////////////////////
		Properties propsC = new Properties();
		propsC.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,  "localhost:9092");
		propsC.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
		propsC.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		propsC.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		propsC.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		BasicConsumer<String, String> consumer = new BasicConsumer<String, String>(propsC);
		consumer.subscribe(KafkaConfig.KAFKA_TOPIC);
        final int giveUp = 100;
        int noRecordsCount = 0;
        
        while (true) {
            final ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);

            if (consumerRecords.count() == 0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                    record.key(), record.value(),
                    record.partition(), record.offset());
            });

            consumer.commitSync(false);
        }
        consumer.close();
        System.out.println("DONE");
        
	}
	
	

}
