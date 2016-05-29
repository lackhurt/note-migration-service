package com.note.migration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.rabbitmq.tools.json.JSONSerializable;
import com.rabbitmq.tools.json.JSONWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Note implements JSONString {
    private String notebookName;
    private List<String> tags;
    private String content;
    private String title;
    private long createdAt;
    private long updatedAt;

    public Note() {
        this.tags = new ArrayList<String>();
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public String toJSONString() {
        return new JSONObject(this).toString();
    }
}
