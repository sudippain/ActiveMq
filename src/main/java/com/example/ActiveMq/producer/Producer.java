package com.example.ActiveMq.producer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ActiveMq.dto.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/produce")
public class Producer {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ActiveMQQueue queue;

	@PostMapping("/message")
	public Student sendMessage(@RequestBody Student student) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			String studentAsJson = mapper.writeValueAsString(student);
			System.out.println(" StudentAsJson  values "+studentAsJson);
			jmsTemplate.setDefaultDestinationName("message-queue"); //Default Queue is selected Here which is message-queue
			jmsTemplate.convertAndSend(studentAsJson);
			
			jmsTemplate.setDefaultDestinationName("message-queue1");
			jmsTemplate.convertAndSend(studentAsJson);  
			
			jmsTemplate.convertAndSend(queue,studentAsJson); //Explicitely giving queue new using autowired of ActiveMQQueue class
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
}