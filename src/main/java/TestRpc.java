import com.evernote.edam.type.Notebook;
import com.note.migration.*;
import com.note.migration.Consumer;
import com.note.migration.evernote.EvernotePuller;
import com.note.migration.evernote.FetchNotebooksProcessor;
import com.oracle.javafx.jmx.json.impl.JSONMessages;
import com.rabbitmq.client.*;
import com.rabbitmq.tools.json.JSONReader;
import com.rabbitmq.tools.json.JSONWriter;
import com.sun.deploy.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lackhurt on 16/5/29.
 */
public class TestRpc {

    public static void main(String args[]) throws Exception {
        com.note.migration.Consumer consumer = new Consumer();
        consumer.registerRPCProcessor(new FetchNotebooksProcessor());
    }
}
