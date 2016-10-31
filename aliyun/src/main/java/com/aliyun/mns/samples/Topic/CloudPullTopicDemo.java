package com.aliyun.mns.samples.Topic;

import java.util.Vector;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudPullTopic;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.common.utils.ServiceSettings;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.QueueMeta;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.aliyun.mns.model.TopicMeta;

public class CloudPullTopicDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start CloudPullTopicDemo");
        String accessKeyId = ServiceSettings.getMNSAccessKeyId();
        String accessKeySecret = ServiceSettings.getMNSAccessKeySecret();
        String endpoint = ServiceSettings.getMNSAccountEndpoint();

        CloudAccount account = new CloudAccount(accessKeyId, accessKeySecret, endpoint);
        MNSClient client = account.getMNSClient();

        // build consumer name list.
        Vector<String> consumerNameList = new Vector<String>();
        String consumerName1 = "consumer001";
        String consumerName2 = "consumer002";
        String consumerName3 = "consumer003";
        consumerNameList.add(consumerName1);
        consumerNameList.add(consumerName2);
        consumerNameList.add(consumerName3);
        QueueMeta queueMetaTemplate = new QueueMeta();
        queueMetaTemplate.setPollingWaitSeconds(30);

        try{
            //producer code:
            // create pull topic which will send message to 3 queues for consumer.
            String topicName = "demo-topic-for-pull";
            TopicMeta topicMeta = new TopicMeta();
            topicMeta.setTopicName(topicName);
            CloudPullTopic pullTopic = client.createPullTopic(topicMeta, consumerNameList, true, queueMetaTemplate);

            //publish message and consume message.
            String messageBody = "broadcast message to all the consumers:hello the world.";
            // if we sent raw message,then should use getMessageBodyAsRawString to parse the message body correctly.
            TopicMessage tMessage = new RawTopicMessage(); 
            tMessage.setBaseMessageBody(messageBody);
            pullTopic.publishMessage(tMessage);

            // consumer code:
            //3 consumers receive the message.
            CloudQueue queueForConsumer1 = client.getQueueRef(consumerName1);
            CloudQueue queueForConsumer2 = client.getQueueRef(consumerName2);
            CloudQueue queueForConsumer3 = client.getQueueRef(consumerName3);

            Message consumer1Msg = queueForConsumer1.popMessage(30);
            if(consumer1Msg != null) 
            {
                System.out.println("consumer1 receive message:" + consumer1Msg.getMessageBodyAsRawString());
            }else{
                System.out.println("the queue is empty");
            }

            Message consumer2Msg = queueForConsumer2.popMessage(30);
            if(consumer2Msg != null) 
            {
                System.out.println("consumer2 receive message:" + consumer2Msg.getMessageBodyAsRawString());
            }else{
                System.out.println("the queue is empty");
            }

            Message consumer3Msg = queueForConsumer3.popMessage(30);
            if(consumer3Msg != null) 
            {
                System.out.println("consumer3 receive message:" + consumer3Msg.getMessageBodyAsRawString());
            }else{
                System.out.println("the queue is empty");
            }

            // delete the fullTopic.
            pullTopic.delete();
        }catch(ClientException ce)
        {
            System.out.println("Something wrong with the network connection between client and MNS service."
                    + "Please check your network and DNS availablity.");
            ce.printStackTrace();
        }
        catch(ServiceException se)
        {
            /*you can get more MNS service error code in following link.
              https://help.aliyun.com/document_detail/mns/api_reference/error_code/error_code.html?spm=5176.docmns/api_reference/error_code/error_response
            */
            se.printStackTrace();
        }

        client.close();
        System.out.println("End CloudPullTopicDemo");
    }

}
