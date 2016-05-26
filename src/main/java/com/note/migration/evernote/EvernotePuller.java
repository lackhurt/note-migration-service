package com.note.migration.evernote;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteList;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteSortOrder;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.Tag;
import com.evernote.thrift.TException;

import java.util.List;

public class EvernotePuller {
    private String accessToken;
    private NoteStoreClient noteStore;

    public EvernotePuller(String accessToken) throws TException, EDAMUserException, EDAMSystemException {
        this.accessToken = accessToken;
        EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.SANDBOX, accessToken);
        ClientFactory factory = new ClientFactory(evernoteAuth);
        noteStore = factory.createNoteStoreClient();
    }


    public List<Notebook> fetchAllNotebooks() throws TException, EDAMUserException, EDAMSystemException {
        return this.noteStore.listNotebooks();
    }

    public List<Tag> fetchAllTags() throws TException, EDAMUserException, EDAMSystemException {
        return this.noteStore.listTags();
    }

    public List<Note> fetchNotesBy(Notebook notebook, int start, int limit) throws EDAMUserException, EDAMSystemException, TException, EDAMNotFoundException {
        NoteFilter filter = new NoteFilter();
        filter.setNotebookGuid(notebook.getGuid());
        filter.setOrder(NoteSortOrder.CREATED.getValue());
        filter.setAscending(true);

        NoteList noteList = noteStore.findNotes(filter, start, limit);
        return noteList.getNotes();
    }

    public List<Note> fetchNotesBy(Notebook notebook) throws EDAMUserException, EDAMSystemException, EDAMNotFoundException, TException {
        this.fetchNotesBy(notebook, 0, 100);
        return null;
    }

    public List<Note> fetchNotesBy(Tag tag) {
        return null;
    }
}
