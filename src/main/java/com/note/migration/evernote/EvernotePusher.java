package com.note.migration.evernote;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.clients.UserStoreClient;
import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.User;
import com.evernote.edam.userstore.UserStore;
import com.evernote.thrift.TException;

import java.util.UUID;

public class EvernotePusher {
    private String accessToken;
    private NoteStoreClient noteStore;

    public EvernotePusher(String accessToken) throws TException, EDAMUserException, EDAMSystemException {
        this.accessToken = accessToken;
        EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.SANDBOX, accessToken);
        ClientFactory factory = new ClientFactory(evernoteAuth);
        noteStore = factory.createNoteStoreClient();
    }

    public Notebook createNotebook(Notebook notebook) throws TException, EDAMUserException, EDAMSystemException {
        return this.noteStore.createNotebook(notebook);
    }

    public Note createNote(Note note) throws EDAMUserException, EDAMSystemException, TException, EDAMNotFoundException {
        return this.noteStore.createNote(note);
    }
}
