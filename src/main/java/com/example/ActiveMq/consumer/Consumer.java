package com.example.ActiveMq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@JmsListener(destination = "message-queue")
	public void consumeMessageFromQueue(String message) {
		logger.info("Message received from activemq queue---"+message);
	}
	@JmsListener(destination = "message-queue1")
	public void consumeMessageFromQueue1(String message) {
		logger.info("Message received from activemq queue1---"+message);
	}
}