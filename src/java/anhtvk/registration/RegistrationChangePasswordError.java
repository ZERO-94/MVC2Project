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
public class RegistrationChangePasswordError {
    
    private String passwordLengthError;
    private String confirmNotMatched;
    private String invalidOldPassword;

    /**
     * @return the passwordLengthError
     */
    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    /**
     * @param passwordLengthError the passwordLengthError to set
     */
    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    /**
     * @return the confirmNotMatched
     */
    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    /**
     * @param confirmNotMatched the confirmNotMatched to set
     */
    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getInvalidOldPassword() {
        return invalidOldPassword;
    }

    public void setInvalidOldPassword(String invalidOldPassword) {
        this.invalidOldPassword = invalidOldPassword;
    }
    
    
    
}
