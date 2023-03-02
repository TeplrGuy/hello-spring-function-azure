package com.example;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.EventHubOutput;
import com.microsoft.azure.functions.annotation.FunctionName;

public class InBlobOutevent {
    @FunctionName("InBlobOutEventTrigger")
    public void run(
        @BlobTrigger(name = "file", dataType = "binary", path = "testing/{name}", connection = "AzureWebJobsStorage2") byte[] content,
        @BindingName("name") String filename,
        @EventHubOutput(name = "event", eventHubName = "myeventhub", connection = "MyEventHubSendAppSetting") OutputBinding<String> event,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Blob trigger function processed a blob.\n Name: " + filename + "\n Size: " + content.length + " Bytes");
        event.setValue("New file: " + filename);
    }
}
