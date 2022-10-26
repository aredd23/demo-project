package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.demo.model.Employee;

@Service
public class KafkaSender {

	@Value(value = "${kafka.employee.topic}")
	private String topicName;
	
	@Value(value = "${kafka.employee.topic1}")
	private String topicName1;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Employee> employeeKafkaTemplate;

	public void send(String message) {

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName1, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println(
						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
			}
		});
	}
	
	public void send(Employee empployee) {

		ListenableFuture<SendResult<String, Employee>> future = employeeKafkaTemplate.send(topicName, empployee);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Employee>>() {

			@Override
			public void onSuccess(SendResult<String, Employee> result) {
				System.out.println(
						"Sent message=[" + empployee + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + empployee + "] due to : " + ex.getMessage());
			}
		});
	}

}
