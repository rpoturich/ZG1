/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventplanner;

/**
 *
 * @author rachelpoturich
 */
public enum Roles {
    
    GENERAL ("general"),
    EDITOR ("editor"),
    ADMIN ("admin")
    ;
    
    private final String role;
    
    private Roles(String r){
        role = r;
    }
    
    public String toString(){
        return role;
    }
    
}
