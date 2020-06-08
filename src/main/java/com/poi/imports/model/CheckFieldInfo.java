package com.poi.imports.model;

public class CheckFieldInfo {
       private String fieldId;
       private String publishTaskId;
       private Integer id;
       private String fieldname;
       private String fieldtype;
       private String lenPrecision;
       private String lenScala;
       private String fieldformat;
       private String checknull;
       private String checkrepeat;
       private String checkenum;
       private String enumvalue;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getPublishTaskId() {
        return publishTaskId;
    }

    public void setPublishTaskId(String publishTaskId) {
        this.publishTaskId = publishTaskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getFieldtype() {
        return fieldtype;
    }

    public void setFieldtype(String fieldtype) {
        this.fieldtype = fieldtype;
    }

    public String getLenPrecision() {
        return lenPrecision;
    }

    public void setLenPrecision(String lenPrecision) {
        this.lenPrecision = lenPrecision;
    }

    public String getLenScala() {
        return lenScala;
    }

    public void setLenScala(String lenScala) {
        this.lenScala = lenScala;
    }

    public String getFieldformat() {
        return fieldformat;
    }

    public void setFieldformat(String fieldformat) {
        this.fieldformat = fieldformat;
    }

    public String getChecknull() {
        return checknull;
    }

    public void setChecknull(String checknull) {
        this.checknull = checknull;
    }

    public String getCheckrepeat() {
        return checkrepeat;
    }

    public void setCheckrepeat(String checkrepeat) {
        this.checkrepeat = checkrepeat;
    }

    public String getCheckenum() {
        return checkenum;
    }

    public void setCheckenum(String checkenum) {
        this.checkenum = checkenum;
    }

    public String getEnumvalue() {
        return enumvalue;
    }

    public void setEnumvalue(String enumvalue) {
        this.enumvalue = enumvalue;
    }

    @Override
    public String toString() {
        return "CheckFieldInfo{" + "fieldId='" + fieldId + '\'' + ", publishTaskId='" + publishTaskId + '\'' + ", id=" + id + ", fieldname='" + fieldname + '\'' + ", fieldtype='" + fieldtype + '\'' + ", lenPrecision='" + lenPrecision + '\'' + ", lenScala='" + lenScala + '\'' + ", fieldformat='" + fieldformat + '\'' + ", checknull='" + checknull + '\'' + ", checkrepeat='" + checkrepeat + '\'' + ", checkenum=" + checkenum + ", enumvalue='" + enumvalue + '\'' + '}';
    }
}
