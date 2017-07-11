package com.welloculus.apigateway.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.welloculus.apigateway.service.MessageService;
import com.welloculus.apigateway.util.CustomLogger;


@RestController
public class MessageController {

  @Autowired
  MessageService eventProcessor;

  static CustomLogger logger = CustomLogger.getLogger();

  private static final String EVENT_CALLBACK = "/event/callback";

  /**
   * Fetch Events Controller
   */
  @ResponseBody
  @RequestMapping(value = EVENT_CALLBACK, method = RequestMethod.POST, consumes = "application/json") //TODO anuj, check
  public Map<String, Object> eventCallback(@RequestBody String message) {
    logger.debugStartOfMethod();
    Map<String, Object> response = new HashMap<String, Object>();
    try {
      eventProcessor.handleMessage(message);
    } catch (Exception e) {
      logger.error(e);
    }
    logger.debugEndOfMethod();
    return response;

  }
}
