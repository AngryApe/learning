package com.ape.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Properties that all tables should have.
 * AngryApe created at 2017-10-23
 */
public abstract class BaseEntity implements Serializable{

    /**
     * Indicate who created this data. Maybe a user or a service or whatever else.
     * column : created_by
     */
    private String createdBy;

    /**
     * Data created time, shouldn't be modified once value be set.
     * column : created_at
     */
    private Date createdAt;

    /**
     * Indicate who last modified the data. Maybe a user or a service or whatever else.
     * column : updated_by
     */
    private String updatedBy;

    /**
     * Last update time. Should be reset to data modify time at every update.
     * column : updated_at
     */
    private Date updatedAt;

    /**
     * Data version. Should plus 1 when update data.
     * column : version
     */
    private int version;

    /**
     * Indicate is this data has been deleted.
     * column : deleted
     */
    private boolean deleted;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
