import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.clients.UserStoreClient;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteList;
import com.evernote.edam.notestore.NoteStore;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteSortOrder;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.User;
import com.evernote.edam.userstore.UserStore;
import com.note.migration.evernote.EvernotePuller;
import com.note.migration.evernote.EvernotePusher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.rabbitmq.client.*;

/**
 * Created by lackhurt on 16/4/24.
 */
public class Test {
    private final static String QUEUE_NAME = "hello";
    public static void main(String args[]) throws Exception {
        String developerToken = "S=s1:U=9268b:E=15c4dae34bc:C=154f5fd0728:P=1cd:A=en-devtoken:V=2:H=c58f985ce3eb71b80b9cd8389fb338f4";

        String pushToken = "S=s1:U=928c7:E=15c4db9cc64:C=154f6089fe8:P=1cd:A=en-devtoken:V=2:H=a1d9350a2f5560fb1dccd40f1f7d29a6";
// Set up the NoteStore client
//        EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.SANDBOX, developerToken);
//        ClientFactory factory = new ClientFactory(evernoteAuth);

        try {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("pl.me");
            factory.setUsername("user");
            factory.setPassword("user");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);

            /*EvernotePuller puller = new EvernotePuller(developerToken);

            EvernotePusher pusher = new EvernotePusher(pushToken);


            puller.fetchAllNotebooks().forEach(notebook -> {
                try {
                    System.out.println(notebook.getName());

                    notebook.setName(notebook.getName() + "3");
                    pusher.createNotebook(notebook);

                    List<Note> list = puller.fetchNotesBy(notebook);

                    if (list != null) {
                        list.forEach(note -> {
                            try {
                                if (note != null) {
//                                    pusher.createNote(note);
                                    System.out.println(note.getTitle());
                                    System.out.println(puller.fetchNoteContent(note));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
*/


            /*NoteStoreClient noteStore = factory.createNoteStoreClient();

            UserStoreClient userStore = factory.createUserStoreClient();

            User user = userStore.getUser();
            UserStore store = new UserStore();

            user.getName();

            System.out.println(user.getUsername());
// Make API calls, passing the developer token as the authenticationToken param
            List<Notebook> notebooks = noteStore.listNotebooks();


            for (Notebook notebook : notebooks) {
//
//                NoteFilter filter = new NoteFilter();
//                filter.setNotebookGuid(notebook.getGuid());
//                filter.setOrder(NoteSortOrder.CREATED.getValue());
//                filter.setAscending(true);
//
//                NoteList noteList = noteStore.findNotes(filter, 0, 100);
//                List<Note> notes = noteList.getNotes();
//                for (Note note : notes) {
//                    System.out.println(" * " + note.getTitle());
//                    System.out.println(" - " + noteStore.getNoteContent(note.getGuid()));
//                }
//
                System.out.println(notebook.getGuid());
////                System.out.println(noteStore.getNoteTagNames(notebook.getGuid()));
//                System.out.println("Notebook: " + notebook.getName());
            }

            Notebook notebook = new Notebook();

            notebook.setGuid(UUID.randomUUID().toString());
            notebook.setName("note-migration-测试-No.4");

            notebook = noteStore.createNotebook(notebook);

            Note note = new Note();

            note.setNotebookGuid(notebook.getGuid());

            note.setTitle("note-migration-测试-Note.No.1");

            String enml_header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+ "<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">" + "<en-note>";
            String enml_footer = "</en-note>";

            note.setContent(enml_header + "内容内容内容" + enml_footer);

            List<String> tags = new ArrayList<String>();

            tags.add("技术");
            tags.add("美女");
            tags.add("大白腿");

            note.setTagNames(tags);

            noteStore.createNote(note);*/

        } catch (Exception e) {
            throw e;
        }

    }
}
