package com.welloculus.apigateway.component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.welloculus.apigateway.service.HealthRecordService;

@Component
public class KafkaRecordConsumer {
	
	@Value("${kafka.servers}")
	String kafkaServers;
	
	@Autowired
	HealthRecordService healthRecordService;
	
	public static final String topicName = "test";
	
	@PostConstruct
	public void startConsumer() throws IOException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// and the consumer
				KafkaConsumer<String, String> consumer;
				Properties properties = new Properties();
				properties.put("bootstrap.servers", kafkaServers);
				properties.put("group.id", "testGroup");
				properties.put("enable.auto.commit", true);
				properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
				properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
				properties.put("session.timeout.ms", 10000);
				properties.put("fetch.min.bytes", 50000);
				properties.put("receive.buffer.bytes", 262144);
				properties.put("max.partition.fetch.bytes", 2097152);
				if (properties.getProperty("group.id") == null) {
					properties.setProperty("group.id", "group-" + new Random().nextInt(100000));
				}
				consumer = new KafkaConsumer<String, String>(properties);
				consumer.subscribe(Arrays.asList(topicName));
				
				System.out.println("===========consumer started!!==============");
				try {			
					while (true) {
						ConsumerRecords<String, String> records = consumer.poll(200);
						if(!records.isEmpty()){
							System.out.println("======New records received from kafka!");
						}
						for (ConsumerRecord<String, String> record : records) {
							try {								
								String topic = record.topic();
								String message = record.value();
								System.out.println("messageReceived!! topic: "+topic+", message: "+message);
								healthRecordService.addRecord(message);
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
					}
				} catch (Exception e) {
					consumer.close();
				}
			}
		}).start();
	}

}
