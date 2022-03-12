/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.registration;

/**
 *
 * @author kiman
 */
public class RegistrationUpdateError {
    private String lastFullnameInput;
    private String lastUpdateUser;
    private String invalidUpdateUser;
    private String invalidLastNameLength;

    /**
     * @return the lastUpdateUser
     */
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * @param lastUpdateUser the lastUpdateUser to set
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getInvalidUpdateUser() {
        return invalidUpdateUser;
    }

    public void setInvalidUpdateUser(String invalidUpdateUser) {
        this.invalidUpdateUser = invalidUpdateUser;
    }

    public String getInvalidLastNameLength() {
        return invalidLastNameLength;
    }

    public void setInvalidLastNameLength(String invalidLastNameLength) {
        this.invalidLastNameLength = invalidLastNameLength;
    }

    public String getLastFullnameInput() {
        return lastFullnameInput;
    }

    public void setLastFullnameInput(String lastFullnameInput) {
        this.lastFullnameInput = lastFullnameInput;
    }

    
    
}
