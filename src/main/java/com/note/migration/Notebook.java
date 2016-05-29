package com.note.migration;

import org.json.JSONObject;
import org.json.JSONString;

import java.util.List;

/**
 * Created by lackhurt on 16/5/29.
 */
public class Notebook implements JSONString {
    private String name;
    private String guid;
    private List<String> tags;
    private List<Note> notes;

    @Override
    public String toJSONString() {
        return new JSONObject(this).toString();
    }
}
