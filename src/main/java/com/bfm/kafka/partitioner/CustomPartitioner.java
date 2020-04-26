/**
 * 
 */
package com.bfm.kafka.partitioner;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

/**
 * @author rohsi
 *
 */
public class CustomPartitioner implements Partitioner {

	public void configure(Map<String, ?> configs) {

	}

	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		return 0;
	}

	public void close() {

	}

}
