package com.spammachine.task.customobjects;

import java.util.ArrayList;

public class Attachment {

    private AttachmentType type;
    private ArrayList<String> data;

    public Attachment(AttachmentType type, ArrayList<String> data) {
        this.type = type;
        this.data = data;
    }

    public Attachment() {
        this.type = AttachmentType.UNKNOWN;
        this.data = new ArrayList<>();
    }

    public AttachmentType getType() {
        return type;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }
}
