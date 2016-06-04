import com.note.migration.Consumer;
import com.note.migration.evernote.FetchNotebooksProcessor;

/**
 * Created by lackhurt on 16/5/29.
 */
public class TestRpc {

    public static void main(String args[]) throws Exception {
        com.note.migration.Consumer consumer = new Consumer();
        consumer.registerRPCProcessor(new FetchNotebooksProcessor());
    }
}
