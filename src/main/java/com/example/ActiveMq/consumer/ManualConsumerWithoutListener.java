package com.example.ActiveMq.consumer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ActiveMq.dto.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/consume")
public class ManualConsumerWithoutListener {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ActiveMQQueue queue;


	@GetMapping("/message")
	public Student consumeMessage() {

		Student student = null;
		try {
			System.out.println("Waitting for get message from ManualConsumer");
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonMessage = (String) jmsTemplate.receiveAndConvert(queue);
			System.out.println(jsonMessage);
			student = mapper.readValue(jsonMessage, Student.class);
			System.out.println("Message Recived "+student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
}
