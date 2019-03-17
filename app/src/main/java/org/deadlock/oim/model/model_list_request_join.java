package org.deadlock.oim.model;

public class model_list_request_join {
    String id,org,status,date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getOrg() {
        return org;
    }

    public String getStatus() {
        return status;
    }
}
