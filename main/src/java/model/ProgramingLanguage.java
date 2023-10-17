/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class ProgramingLanguage {
    private int languageId;
    private String languageName;
    private String languageStatus;

    public ProgramingLanguage() {
    }

    public ProgramingLanguage(int languageId, String languageName, String languageStatus) {
        this.languageId = languageId;
        this.languageName = languageName;
        this.languageStatus = languageStatus;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageStatus() {
        return languageStatus;
    }

    public void setLanguageStatus(String languageStatus) {
        this.languageStatus = languageStatus;
    }

}
