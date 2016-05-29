package com.note.migration.evernote;

import com.evernote.edam.type.Notebook;
import com.note.migration.Processor;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lackhurt on 16/5/29.
 */
public class FetchNotebooksProcessor implements Processor {

    private static final String QUEUE_NAME = "fetch-notebooks";

    @Override
    public String process(String message) throws Exception {
        List<String> notebookName = new ArrayList<String>();

        try {

//            JSONArray jsonArray = new JSONArray();
            /*ListNotebooksProcessor.fetchNotebooks(message).forEach(notebook -> {
                JSONObject notebookJSON = new JSONObject();
                notebookJSON.put("name", notebook.getName());
                notebookJSON.put("guid", notebook.getGuid());
                jsonArray.put(notebookJSON);
            });*/

            JSONArray jsonArray = new JSONArray(FetchNotebooksProcessor.fetchNotebooks(message));
            return jsonArray.toString();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public String getQueueName() {
        return FetchNotebooksProcessor.QUEUE_NAME;
    }

    private static List<Notebook> fetchNotebooks(String developerToken) throws Exception {
        EvernotePuller puller = new EvernotePuller(developerToken);
        return puller.fetchAllNotebooks();
    }
}
