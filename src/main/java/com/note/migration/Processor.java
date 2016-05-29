package com.note.migration;

/**
 * Created by lackhurt on 16/5/29.
 */
public interface Processor {
    public String getQueueName();
    public String process(String message) throws Exception;
}
