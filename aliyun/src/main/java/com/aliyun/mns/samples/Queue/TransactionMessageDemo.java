package com.aliyun.mns.samples.Queue;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.client.TransactionChecker;
import com.aliyun.mns.client.TransactionOperations;
import com.aliyun.mns.client.TransactionQueue;
import com.aliyun.mns.common.utils.ServiceSettings;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.QueueMeta;

public class TransactionMessageDemo{
    public class MyTransactionChecker implements TransactionChecker
    {
        public boolean checkTransactionStatus(Message message)
        {
            boolean checkResult = false;
            String messageHandler = message.getReceiptHandle();
            try{
                //TODO: check if the messageHandler related transaction is success.
                checkResult = true;
            }catch(Exception e)
            {
                checkResult = false;
            }
            return checkResult;
        }
    }

    public class MyTransactionOperations implements TransactionOperations
    {
        public boolean doTransaction(Message message)
        {
            boolean transactionResult = false;
            String messageHandler = message.getReceiptHandle();
            String messageBody = message.getMessageBody();
            try{
                //TODO: do your local transaction according to the messageHandler and messageBody here.
                transactionResult = true;
            }catch(Exception e)
            {
                transactionResult = false;
            }
            return transactionResult;
        }
    }

    public static void main(String[] args) {
        System.out.println("Start TransactionMessageDemo");
        String transQueueName = "transQueueName";
        String accessKeyId = ServiceSettings.getMNSAccessKeyId();
        String accessKeySecret = ServiceSettings.getMNSAccessKeySecret();
        String endpoint = ServiceSettings.getMNSAccountEndpoint();
	
        CloudAccount account = new CloudAccount(accessKeyId, accessKeySecret, endpoint);
        MNSClient client = account.getMNSClient(); //this client need only initialize once

        // create queue for transaction queue.
        QueueMeta queueMeta = new QueueMeta();
        queueMeta.setQueueName(transQueueName);
        queueMeta.setPollingWaitSeconds(15);
        
        TransactionMessageDemo demo = new TransactionMessageDemo();
        TransactionChecker transChecker = demo.new MyTransactionChecker();
        TransactionOperations transOperations = demo.new MyTransactionOperations();
        
        TransactionQueue transQueue = client.createTransQueue(queueMeta, transChecker);

        // do transaction.
        Message msg = new Message();
        String messageBody = "TransactionMessageDemo";
        msg.setMessageBody(messageBody); 
        transQueue.sendTransMessage(msg, transOperations);

        // delete queue and close client if we won't use them.
        transQueue.delete();

        // close the client at the end.
        client.close();
        System.out.println("End TransactionMessageDemo");
    }

}
