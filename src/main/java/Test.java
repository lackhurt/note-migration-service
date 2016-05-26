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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lackhurt on 16/4/24.
 */
public class Test {
    public static void main(String args[]) throws Exception {
        String developerToken = "S=s1:U=9268b:E=15bd16fd885:C=15479bea978:P=185:A=january9527:V=2:H=b7ad250f113e99ef2c21e5042ce2fba2";

// Set up the NoteStore client
        EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.SANDBOX, developerToken);
        ClientFactory factory = new ClientFactory(evernoteAuth);

        try {

            NoteStoreClient noteStore = factory.createNoteStoreClient();

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

            noteStore.createNote(note);

        } catch (Exception e) {
            throw e;
        }

    }
}
