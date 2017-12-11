package com.pavel_kaminsky.googlehome_broadlink_bridge.models;

import java.io.Serializable;

/**
 * Created by PK on 12/9/2017.
 */

public class Command implements Serializable {

    String command;
    String date;
    String data;

    public Command() {
    }

    public Command(String command, String date) {
        this.command = command;
        this.date = date;
        this.data = null;
    }

    public Command(String command, String date, String data) {
        this.command = command;
        this.date = date;
        this.data = data;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (this.command != null ? !this.command.equals(command.command) : command.command != null) return false;
        if (date != null ? !date.equals(command.date) : command.date != null) return false;
        return data != null ? data.equals(command.data) : command.data == null;
    }

    @Override
    public int hashCode() {
        int result = command != null ? command.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
